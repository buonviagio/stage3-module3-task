package com.mjc.school.controller.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Annotation marking a method parameter as requiring command parameter injection.
 * Used to identify parameters that should be populated from command execution context.
 */
 @Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandParam {
    /**
     * Specifies the parameter name or binding key.
     * @return The name/key used to identify the parameter value
     */
    String value();
}
