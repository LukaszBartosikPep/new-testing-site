package com.automatedtest.poc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The execution of a  method annotated with this annotations will be automatically logged
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LogMethod {
    boolean isRun() default true;
}
