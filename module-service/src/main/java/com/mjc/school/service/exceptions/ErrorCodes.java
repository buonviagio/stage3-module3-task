package com.mjc.school.service.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * Enumeration of application error codes and messages.
 *
 * Centralizes error information with consistent formatting for:
 *
 *   Client-facing error messages
 *   Troubleshooting and support
 *
 */
@Slf4j
public enum ErrorCodes {
    // News-related errors
    NEW_ID_N_EXIST("000001", "News with id %d does not exist."),

    // Author-related errors
    AUTH_ID_NOT_EXIST("000002", "Author Id does not exist. Author Id is: %s"),

    // Validation errors
    NEGATIVE_OR_NULL_NUMBER("000010", "%s can not be null or less than 1. %s is: %s"),
    NULL_STRING("000011", "%s can not be null. %s is null"),
    STRING_LANGTH("000012", "%s can not be less than %d and more than %d symbols. %s is %s"),
    VALID_NUMBER_VALUE("000013", "%s should be number");

    private String code, message;

    /**
     * Constructs an error code enum value.
     *
     * @param code The unique error code (for machine processing)
     * @param message The error message template (for human display)
     */
    ErrorCodes (String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Returns the string representation of the error.
     *
     * @return Formatted string containing both code and message
     */
    @Override
    public String toString() {
        return "ERROR_CODE: " + code + " ERROR_MESSAGE: " + message;
    }
}

