package aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 防重复提交注解
 * </p>
 * @author guozl
 * @since 2023-04-13
 */
@Component
@Aspect
public class LimitSubmitAspect {
    private static final Logger logger = LoggerFactory.getLogger(LimitSubmitAspect.class);
    @Autowired
    private StringRedisTemplate redisUtils;

    @Pointcut("@annotation(cn.gov.zjport.kwe.base.common.annotation.LimitSubmit)")
    private void pointcut() {}

    /**
     * 防重复提交处理
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object handleSubmit(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String userId = this.getUserIdByToken(request);
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //获取注解
        LimitSubmit limitSubmit = method.getAnnotation(LimitSubmit.class);
        int submitTimeLimiter = limitSubmit.limit();
        String key = limitSubmit.key();
        boolean needAllWait = limitSubmit.needAllWait();
        String redisKey = getRedisKey(userId, joinPoint, key);
        Object result = redisUtils.opsForValue().get(redisKey);
        if (needAllWait && Objects.nonNull(result)) {
            throw new RuntimeException("请勿重复提交！");
        }
        if (!needAllWait && Objects.nonNull(result)) {
            throw new RuntimeException("处理中，请稍后！");
        }
        redisUtils.opsForValue().set(redisKey, userId, submitTimeLimiter);
        try {
            Object proceed = joinPoint.proceed();
            return proceed;
        } catch (Throwable e) {
            logger.error("Exception in {}.{}() with cause = \'{}\' and exception = \'{}\'", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL", e.getMessage(), e);
            throw e;
        } finally {
            if (!needAllWait) {
                redisUtils.delete(redisKey);
            }
        }
    }

    private String getUserIdByToken(HttpServletRequest request) {
        return "userId";// 从request中获取token，可以使用jwt
    }

    /**
     * 支持多参数，从请求参数进行处理
     */
    private String getRedisKey(String userId, ProceedingJoinPoint joinPoint, String redisKey) {
        if (redisKey.contains("%s")) {
            redisKey = String.format(redisKey, userId);
        }
        try {
            JSONObject object = (JSONObject) JSON.toJSON(joinPoint.getArgs()[0]);
            for (Map.Entry<String, Object> entry : object.entrySet()) {
                String key = entry.getKey();
                if (redisKey.contains("#" + entry.getKey())) {
                    redisKey = redisKey.replace("#" + key, entry.getValue().toString());
                }
            }
        } catch (Exception e) {
            logger.error("getRedisKey error userId:{},redisKey:{}", userId, redisKey, e);
        }
        return redisKey;
    }
}
