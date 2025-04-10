package com.mjc.school.controller.command;

/**
 * An abstract base class representing the Command Pattern.
 *
 * The Command Pattern encapsulates a request as an object, thereby allowing for
 * parameterization of clients with different requests, queuing of requests, and
 * support for undoable operations. This abstract class defines the common interface
 * for all concrete command implementations.
 *
 * Concrete command classes should implement the {@code execute()} method to
 * perform specific actions. The result type {@code <T>} allows commands to return
 * different types of results based on their purpose.
 *
 * @param <T> The type of the result produced by the command's execution.
 */
public abstract class Command<T> {
    public abstract T execute();
}
