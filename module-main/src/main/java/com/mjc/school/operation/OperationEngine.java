package com.mjc.school.operation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandBody;
import com.mjc.school.controller.annotations.CommandParam;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.command.Command;
import com.mjc.school.exception.CommandNotFoundException;
import com.mjc.school.exception.NotNumberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Core engine that executes operations based on user input.
 * Coordinates between command reading, controller routing, and method invocation.
 */
@Slf4j
@Component
public class OperationEngine {
    @Autowired
    private OperationReader operationReader;
    private final List<BaseController<?, ?, ?>> controllers;

    @Autowired
    public OperationEngine(OperationReader operationReader, List<BaseController<?, ?, ?>> controllers) {
        this.operationReader = operationReader;
        this.controllers = controllers;
        log.info("Initialized with {} controllers: {}", controllers.size(), controllers.getClass().getSimpleName());
    }

    /**
     * Displays available operations menu to the user
     */
    public void printMenu() {
        log.debug("Displaying operations menu");
        System.out.println("Enter the number of operation:");
        for (Operations operations : Operations.values()) {
            System.out.println(operations);
        }
    }

    /**
     * Executes the operation based on user input
     * @param str The command number/input
     * @param scanner For reading additional command parameters
     */
    public void runOperations(String str, Scanner scanner) {
        log.info("Processing operation input: '{}'", str);
        try {
            Operations operation = Operations.getCommand(str);
            log.debug("Resolved operation: {}", operation);

            Command command = operationReader.readCommandParameters(operation, scanner);
            log.trace("Prepared command: {}", command);

            execute(operation, command);
        } catch (CommandNotFoundException | NotNumberException e) {
            log.warn("Invalid operation input: {}", e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    /**
     * Finds and invokes the appropriate handler method
     * @throws CommandNotFoundException if no suitable handler found
     */
    private void execute(Operations operation, Command command) throws CommandNotFoundException {
        boolean methodFound = false;
        for (BaseController<?, ?, ?> controller : controllers) {

            Optional<Method> optionalMethod = Arrays.stream(controller.getClass().getDeclaredMethods())
                    .filter(x -> x.isAnnotationPresent(CommandHandler.class))
                    .filter(x -> x.getAnnotation(CommandHandler.class).operation().equals(operation.getCommand()))
                    .findFirst();

            if (optionalMethod.isPresent()) {
                Method method = optionalMethod.get();

                try {
                    Object[] args = prepareArguments(method, command);
                    log.debug("Invoking {} with {} arguments", method.getName(), args.length);
                    // at this point we receive return from methods from classes AuthorController and NewsController
                    method.invoke(controller, args);
                    methodFound = true;
                    break;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println(e.getCause().getMessage());
                }
            }
        }
        if (!methodFound) {
            log.error("No handler found for operation: {}", operation);
            throw new CommandNotFoundException("Command not found, we do not perform such operation");
        }
    }

    private Object[] prepareArguments(Method method, Command command) {
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(CommandBody.class)) {
                args[i] = command.execute();
                log.trace("Prepared @CommandBody argument: {}", args[i]);
            } else if (parameters[i].isAnnotationPresent(CommandParam.class)) {
                args[i] = extractCommandParam(command, parameters[i].getType());
                log.trace("Prepared @CommandParam argument: {}", args[i]);
            }
        }
        return args;
    }

    private Object extractCommandParam(Command command, Class<?> paramType) {
        if (paramType.equals(Long.class)) {
            return command.execute();
        }
        log.warn("Unsupported parameter type: {}", paramType);
        return null;
    }
}

