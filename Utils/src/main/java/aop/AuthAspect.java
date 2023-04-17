package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 登录拦截，
 * 1、可以使用aop切面做，
 * 2、也可以使用拦截器做
 */

@Aspect
@Component
public class AuthAspect {
    /**
     * 忽略url
     */
    @Value("${partner.permission.excludeUrls:/auth/getCaptcha,/etcSysUser/toLogin,/auth/getToken,/etcSysUser/auth/login,/etcVehicleManage/vehicle}")
    private String excludeUrls;

    @Autowired
    private StringRedisTemplate redisUtils;

    /**
     * 指定切入点（规定切面范围）
     * cn.gov.zjport.kwe.partner.webapp.controller
     */
    @Pointcut("execution(* cn.gov.zjport.kwe.partner.webapp.controller.*.*(..))")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String path = request.getServletPath();
        if (isExclude(path)) {
            return pjp.proceed();
        }
        String token = this.getToken(request);
        if (StringUtils.isEmpty(token)) {
            return "请重新登录";
        }
        String cacheToken = redisUtils.opsForValue().get("token_prefix" +  token);
        if (StringUtils.isEmpty(cacheToken)) {
            return "登录状态失效，请重新登录";
        }
        redisUtils.opsForValue().set("token_prefix" + token, token, 60000 * 24 * 30);
        return pjp.proceed();
    }

    private String getToken(HttpServletRequest request) {
        return "";
    }

    private boolean isExclude(String path) {
        return StringUtils.isEmpty(excludeUrls) && StringUtils.isEmpty(path) && Arrays.stream(excludeUrls.split(",")).filter(c -> path.indexOf(c) >= 0).count() > 0;
    }


}
