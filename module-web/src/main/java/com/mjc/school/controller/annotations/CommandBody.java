package com.mjc.school.controller.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Marks a method parameter as receiving the complete command body.
 *
 * Used on handler method parameters to indicate that the parameter should be
 * populated with the result of executing the command.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandBody {
}
