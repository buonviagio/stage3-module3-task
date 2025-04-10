package com.mjc.school.controller.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Marks a method as a handler for specific command operations.
 *
 * Methods annotated with {@code @CommandHandler} will be invoked when processing
 * commands matching their specified operation. Used in conjunction with the Command Pattern
 * implementation to route command execution to appropriate handler methods.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CommandHandler {
    /**
     * The operation identifier this handler responds to.
     * Should match one of the operation names defined in the {@code Operations} enum.
     *
     * @return The operation name this handler processes
     */
    String operation();
}
