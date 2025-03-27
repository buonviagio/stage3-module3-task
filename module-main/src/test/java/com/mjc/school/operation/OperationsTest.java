package com.mjc.school.operation;

import com.mjc.school.exception.CommandNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {
    @Test
    @DisplayName("Valid command number returns correct operation")
    void getCommand_validNumber_returnsOperation() throws CommandNotFoundException {
        // Test happy path
        Operations result = Operations.getCommand("1");
        assertEquals(Operations.GET_ALL_NEWS, result);

        // Test case sensitivity
        Operations result5 = Operations.getCommand("5");
        assertEquals(Operations.CREATE_NEWS, result5);
    }

    @Test
    @DisplayName("Invalid command number throws exception")
    void getCommand_invalidNumber_throwsException() {
        // Test exception throwing
        Exception exception = assertThrows(
                CommandNotFoundException.class,
                () -> Operations.getCommand("99")
        );

        assertTrue(exception.getMessage().contains("Command not found: 99"));
    }

    @Test
    @DisplayName("toString returns formatted string")
    void toString_returnsFormattedString() {
        String result = Operations.GET_ALL_NEWS.toString();
        assertEquals("1 - Get all news", result);
    }

    @Test
    @DisplayName("All command numbers are unique")
    void allCommandNumbers_unique() {
        long uniqueCount = Arrays.stream(Operations.values())
                .map(Operations::getCommandNum)
                .distinct()
                .count();

        assertEquals(Operations.values().length, uniqueCount);
    }
}