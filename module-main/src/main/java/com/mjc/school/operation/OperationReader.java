package com.mjc.school.operation;

import com.mjc.school.controller.command.Command;
import com.mjc.school.exception.CommandNotFoundException;
import com.mjc.school.exception.NotNumberException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class OperationReader {
    public Command readCommandParameters(Operations operation, Scanner scanner) throws CommandNotFoundException, NotNumberException {
        switch (operation) {
            case GET_ALL_NEWS -> {
               return null;
                //return new GetAllNewsCommand();
            }
            case GET_ALL_AUTHORS -> {
                return null;
                //return new GetAllAutorCommand();
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
            case EXIT -> System.exit(0);
            default -> throw new CommandNotFoundException("Command not found");
        }
        return null;
    }

    private Command  readNewsById(Scanner scanner, Operations operations) throws NotNumberException {
        System.out.println("Operation: " + operations.getCommand());
        String id = readNumber(scanner, "news");
        return new Command();
        //return new GetNewsByIdCommand(Long.parseLong(id));
    }

    private Command  readAuthorById(Scanner scanner, Operations operations) throws NotNumberException {
        System.out.println("Operation: " + operations.getCommand());
        String id = readNumber(scanner, "author");
        return new Command();
        //return new GetAuthorByIdCommand(Long.parseLong(id));
    }

    private Command  deleteNewsById(Scanner scanner, Operations operations) throws NotNumberException {
        System.out.println("Operation: " + operations.getCommand());
        String id = readNumber(scanner, "news");
        return new Command();
        //return new DeleteNewsByIdCommand(Long.parseLong(id));
    }

    private Command  deleteAuthorById(Scanner scanner, Operations operations) throws NotNumberException {
        System.out.println("Operation: " + operations.getCommand());
        String id = readNumber(scanner, "author");
        return new Command();
        //return new DeleteAuthorByIdCommand(Long.parseLong(id));
    }

    private Command createNews(Scanner scanner) throws NotNumberException {
        System.out.println("Operation: Create news.");
        String title = readString(scanner, "news", "title");
        String content = readString(scanner, "news", "content");
        Long authorId =  Long.parseLong(readNumber(scanner, "author"));
        return new Command();
        //return new CreateNewsCommand(title, content, authorId);
    }
    //AuthorDtoRequest
    private Command createAuthor(Scanner scanner) {
        System.out.println("Operation: Create author.");
        String name = readString(scanner, "author", "name");
        return new Command();
        //return new CreateAuthorCommand(name);
    }
    //NewsDtoRequest
    private Command updateNews(Scanner scanner) throws NotNumberException {
        System.out.println("Operation: Update news.");
        Long newsId =  Long.parseLong(readNumber(scanner, "newsId"));
        String title = readString(scanner, "news", "title");
        String content = readString(scanner, "news", "content");
        Long authorId =  Long.parseLong(readNumber(scanner, "author"));
        return new Command();
        //return new UpdateNewsCommand(newsId, title, content, authorId);
    }
    //AuthorDtoRequest
    private Command updateAuthor(Scanner scanner) throws NotNumberException {
        System.out.println("Operation: Update author.");
        Map<String, String> map = new HashMap<>();
        Long authorId = Long.parseLong(readNumber(scanner, "author"));
        String name = readString(scanner, "author", "name");
        return new Command();
        //return new UpdateAuthorCommand(authorId, name);
    }

    private String readString(Scanner scanner, String param1, String param2) {
        System.out.format("Enter %s %s: ", param1, param2);
        String input;
        do {
            input = scanner.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    private String readNumber(Scanner scanner, String param) throws NotNumberException {
        System.out.format("Enter %s id: ", param);
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
