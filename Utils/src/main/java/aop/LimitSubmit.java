package aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * </p>
 * @author guozl
 * @since 2023-04-13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LimitSubmit {
    String key() ;
    /**
     * 默认 10s
     */
    int limit() default 5;

    /**
     * 请求完成后 是否一直等待
     * true则等待
     * @return
     */
    boolean needAllWait() default true;
}
