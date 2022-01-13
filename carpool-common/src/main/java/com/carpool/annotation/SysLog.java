package com.carpool.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author bjsonghongxu
 * @email songhongxucg@gmail.com
 * @date 2017年3月8日 上午10:19:56
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "操作日志";
}
