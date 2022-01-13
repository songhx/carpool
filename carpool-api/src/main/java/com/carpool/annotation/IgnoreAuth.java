package com.carpool.annotation;

import java.lang.annotation.*;

/**
 * 忽略Token验证
 *
 * @author bjsonghongxu
 * @email songhongxucg@gmail.com
 * @date 2017-03-23 15:44
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {

}
