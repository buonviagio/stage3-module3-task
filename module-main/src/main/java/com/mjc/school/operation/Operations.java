package com.mjc.school.operation;

import com.mjc.school.exception.CommandNotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * Enum for representing different operations in the application
 * Each operation has:
 * - A descriptive name (e.g., "Get all news")
 * - A unique command number (e.g., "1")
 *
 * Used to map user input to system operations.
 * */
    @Slf4j
    public enum Operations {
        /**
         * Defining constants with command descriptions and command numbers
         * */
        GET_ALL_NEWS("Get all news", "1"),
        GET_ALL_AUTHORS("Get all authors", "2"),
        GET_NEWS_BY_ID("Get news by id", "3"),
        GET_AUTHOR_BY_ID("Get author by id", "4"),
        CREATE_NEWS("Create news", "5"),
        CREATE_AUTHOR("Create author", "6"),
        UPDATE_NEWS("Update news", "7"),
        UPDATE_AUTHORS("Update authors", "8"),
        REMOVE_NEWS_BY_ID("Remove news by id", "9"),
        REMOVE_AUTHOR_BY_ID("Remove author by id", "10"),
        EXIT("Exit", "0");

        private String command;
        private String commandNum;

        /**
         * Enum constructor (private by default).
         * @param command    The operation description
         * @param commandNum The numeric command code
         */
        Operations(String command, String commandNum) {
            this.command = command;
            this.commandNum = commandNum;
        }

        /**
         * Finds an operation by its command number.
         * @param number The command number to search for
         * @return Matching Operations enum constant
         * @throws CommandNotFoundException If no operation matches the number
         */
        public static Operations getCommand(String number) throws CommandNotFoundException {
            log.debug("Looking up command by number: {}", number);
            for (Operations operation : Operations.values()){
                if(operation.commandNum.equals(number)){
                    log.trace("Found matching operation: {}", operation);
                    return operation;
                }
            }
            log.warn("Command not found for number: {}", number);
            throw new CommandNotFoundException("Command not found: " + number);
        }

        public String getCommand() {
            return command;
        }

        public String getCommandNum() {
            return commandNum;
        }

        /**
         * Formats the operation for display.
         * @return String in "X - Description" format "1 - Get all news"
         */
        @Override
        public String toString() {
            return String.format("%s - %s", commandNum, command);
        }
    }

