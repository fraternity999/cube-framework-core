package com.cube.core.system.annotation;

import java.lang.annotation.*;

/**
 * @author YYF
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";
}
