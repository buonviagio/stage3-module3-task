package com.mjc.school.operation;

import com.mjc.school.controller.command.*;
import com.mjc.school.exception.CommandNotFoundException;
import com.mjc.school.exception.NotNumberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Component responsible for reading command parameters from user input
 * and creating appropriate Command objects.
 *
 * <p>Handles all user interaction for command parameter collection
 * and validates numeric input.
 */
@Slf4j
@Component
public class OperationReader {
    /**
     * Creates a Command object based on the specified operation and user input.
     *
     * @param operation The operation type to create a command for
     * @param scanner Scanner instance for reading user input
     * @return Appropriate Command implementation
     * @throws CommandNotFoundException If operation is not supported
     * @throws NotNumberException If numeric input validation fails
     */
    public Command<?> readCommandParameters(Operations operation, Scanner scanner) throws CommandNotFoundException, NotNumberException {
        switch (operation) {
            case GET_ALL_NEWS -> {
                return new GetAllNewsCommand();
            }
            case GET_ALL_AUTHORS -> {
                return new GetAllAuthorCommand();
            }
            case GET_NEWS_BY_ID -> {
                return readNewsById(scanner,  operation);
            }
            case REMOVE_NEWS_BY_ID -> {
                return deleteNewsById(scanner,  operation);
            }
            case GET_AUTHOR_BY_ID -> {
                return readAuthorById(scanner, operation);
            }
            case REMOVE_AUTHOR_BY_ID -> {
                return deleteAuthorById(scanner, operation);
            }
            case CREATE_NEWS -> {
                return createNews(scanner);
            }
            case CREATE_AUTHOR -> {
                return createAuthor(scanner);
            }
            case UPDATE_NEWS -> {
                return updateNews(scanner);
            }
            case UPDATE_AUTHORS -> {
                return updateAuthor(scanner);
            }
            case GET_ALL_TAGS -> {
                return new GetAllTagsCommand();
            }
            case GET_TAG_BY_ID -> {
                return readTagById(scanner,  operation);
            }
            case CREATE_TAG -> {
                return createTag(scanner);
            }
            case UPDATE_TAG -> {
                return updateTag(scanner);
            }
            case DELETE_TAG -> {
                return deleteTagById(scanner, operation);
            }
            case GET_AUTHOR_BY_NEWS_ID -> {
                return readAuthorByNewsId(scanner, operation);
            }
            case GET_TAGS_BY_NEWS_ID -> {
                return readTagByNewsId(scanner, operation);
            }
            case GET_NEWS_BY_SOME_PARAMETERS -> {
                return getNewsByParams(scanner, operation);
            }
            case EXIT -> System.exit(0);
            default -> throw new CommandNotFoundException("Command not found");
        }
        return null;
    }

    private Command<Long>  readNewsById(Scanner scanner, Operations operations) throws NotNumberException {
        log.info("Reading parameters for GetNewsByIdCommand");
        System.out.println("Operation: " + operations.getCommand());
        String id = readNumber(scanner, "news");
        log.debug("Creating GetNewsByIdCommand with ID: {}", id);
        return new GetNewsByIdCommand(Long.parseLong(id));
    }

    private Command<Long>  readAuthorById(Scanner scanner, Operations operations) throws NotNumberException {
        log.info("Reading parameters for GetAuthorByIdCommand");
        System.out.println("Operation: " + operations.getCommand());
        String id = readNumber(scanner, "author");
        log.debug("Creating GetAuthorByIdCommand with ID: {}", id);
        return new GetAuthorByIdCommand(Long.parseLong(id));
    }

    private Command<Long>  readTagById(Scanner scanner, Operations operations) throws NotNumberException {
        log.info("Reading parameters for GetTagByIdCommand");
        System.out.println("Operation: " + operations.getCommand());
        String id = readNumber(scanner, "author");
        log.debug("Creating GetTagByIdCommand with ID: {}", id);
        return new GetTagByIdCommand(Long.parseLong(id));
    }

    private Command<Long>  readTagByNewsId(Scanner scanner, Operations operations) throws NotNumberException {
        log.info("Reading parameters for GetTagByNewsIdCommand");
        System.out.println("Operation: " + operations.getCommand());
        String id = readNumber(scanner, "news");
        log.debug("Creating GetTagByNewsIdCommand with ID: {}", id);
        return new GetTagByIdCommand(Long.parseLong(id));
    }


    private Command<Long>  deleteNewsById(Scanner scanner, Operations operations) throws NotNumberException {
        log.info("Reading parameters for DeleteNewsByIdCommand");
        System.out.println("Operation: " + operations.getCommand());
        String id = readNumber(scanner, "news");
        log.debug("Creating DeleteNewsByIdCommand with ID: {}", id);
        return new DeleteNewsByIdCommand(Long.parseLong(id));
    }

    private Command<Long>  deleteAuthorById(Scanner scanner, Operations operations) throws NotNumberException {
        log.info("Reading parameters for DeleteAuthorByIdCommand");
        System.out.println("Operation: " + operations.getCommand());
        String id = readNumber(scanner, "author");
        log.debug("Creating DeleteAuthorByIdCommand with ID: {}", id);
        return new DeleteAuthorByIdCommand(Long.parseLong(id));
    }

    private Command<Long>  deleteTagById(Scanner scanner, Operations operations) throws NotNumberException {
        log.info("Reading parameters for DeleteTagByIdCommand");
        System.out.println("Operation: " + operations.getCommand());
        String id = readNumber(scanner, "tag");
        log.debug("Creating DeleteTagByIdCommand with ID: {}", id);
        return new DeleteTagByIdCommand(Long.parseLong(id));
    }

    private Command<?> createNews(Scanner scanner) throws NotNumberException {
        log.info("Reading parameters for CreateNewsCommand");
        System.out.println("Operation: Create news.");
        String title = readString(scanner, "news", "title");
        String content = readString(scanner, "news", "content");
        Long authorId =  Long.parseLong(readNumber(scanner, "author"));
        log.debug("Creating CreateNewsCommand with title: {}, authorId: {}", title, authorId);
        return new CreateNewsCommand(title, content, authorId);
    }

    private Command<?> createAuthor(Scanner scanner) {
        log.info("Reading parameters for CreateAuthorCommand");
        System.out.println("Operation: Create author.");
        String name = readString(scanner, "author", "name");
        log.debug("Creating CreateAuthorCommand with name: {}", name);
        return new CreateAuthorCommand(name);
    }

    private Command<?> createTag(Scanner scanner) {
        log.info("Reading parameters for CreateTagCommand");
        System.out.println("Operation: Create tag.");
        String name = readString(scanner, "tag", "name");
        log.debug("Creating CreateTagCommand with name: {}", name);
        return new CreateTagCommand(name);
    }

    private Command<?> updateNews(Scanner scanner) throws NotNumberException {
        log.info("Reading parameters for UpdateNewsCommand");
        System.out.println("Operation: Update news.");
        Long newsId =  Long.parseLong(readNumber(scanner, "newsId"));
        String title = readString(scanner, "news", "title");
        String content = readString(scanner, "news", "content");
        Long authorId =  Long.parseLong(readNumber(scanner, "author"));
        log.debug("Creating UpdateNewsCommand for ID: {}", newsId);
        return new UpdateNewsCommand(newsId, title, content, authorId);
    }

    private Command<?> updateAuthor(Scanner scanner) throws NotNumberException {
        log.info("Reading parameters for UpdateAuthorCommand");
        System.out.println("Operation: Update author.");
        Map<String, String> map = new HashMap<>();
        Long authorId = Long.parseLong(readNumber(scanner, "author"));
        String name = readString(scanner, "author", "name");
        log.debug("Creating UpdateAuthorCommand for ID: {}", authorId);
        return new UpdateAuthorCommand(authorId, name);
    }

    private Command<?> updateTag(Scanner scanner) throws NotNumberException {
        log.info("Reading parameters for UpdateTagCommand");
        System.out.println("Operation: Update tag.");
        Map<String, String> map = new HashMap<>();
        Long tagId = Long.parseLong(readNumber(scanner, "tag"));
        String name = readString(scanner, "tag", "name");
        log.debug("Creating UpdateTagCommand for ID: {}", tagId);
        return new UpdateTagCommand(tagId, name);
    }

    private Command<Long>  readAuthorByNewsId(Scanner scanner, Operations operations) throws NotNumberException {
        log.info("Reading parameters for GetAuthorByNewsIdCommand");
        System.out.println("Operation: " + operations.getCommand());
        String id = readNumber(scanner, "news");
        log.debug("Creating GetAuthorByNewsIdCommand with ID: {}", id);
        return new GetAuthorByNewsIdCommand(Long.parseLong(id));
    }

    private Command<?>  getNewsByParams(Scanner scanner, Operations operations) throws NotNumberException {
        Map<String, String> map = new HashMap<>();
        log.info("Reading parameters for GetNewsByParamsCommand");
        System.out.println("Operation: " + operations.getCommand() + " all parameters are optional, if you want to skipp one parameter type 0");
        map.put("tag", readString(scanner, "tag", ""));
        map.put("tag_id", readString(scanner, "tag", "id"));
        map.put("author_name", readString(scanner, "author", "name"));
        map.put("title", readString(scanner, "title", ""));
        map.put("content", readString(scanner, "some",  "content"));
        return new GetNewsByParametersCommand(map);
    }
    /**
     * Reads a string input from the user with validation for empty values.
     *
     * @param scanner Scanner for user input
     * @param param1 The type of entity being entered (e.g., "news", "author")
     * @param param2 The field being entered (e.g., "title", "name")
     * @return Non-empty trimmed string
     */
    private String readString(Scanner scanner, String param1, String param2) {
        System.out.format("Enter %s %s: ", param1, param2);
        String input;
        do {
            input = scanner.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    /**
     * Reads a numeric ID input from the user with validation.
     *
     * @param scanner Scanner for user input
     * @param paramName The parameter name being entered (e.g., "news", "author")
     * @return Valid numeric string
     * @throws NotNumberException If input is not numeric
     */

    private String readNumber(Scanner scanner, String paramName) throws NotNumberException {
        System.out.format("Enter %s id: ", paramName);
        String input;
        do {
            input = scanner.nextLine().trim();
        } while (input.isEmpty());
        if (input.matches("\\d*")) {
            return input;
        } else {
            throw new NotNumberException(String.format("%s should be number", input));
        }
    }
}
