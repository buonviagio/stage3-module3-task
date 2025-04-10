package com.mjc.school.controller.command;

import com.mjc.school.service.dto.AuthorDtoRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Command implementation for creating a new author.
 * This command encapsulates the author creation request data and returns
 * the prepared AuthorDtoRequest when executed.
 */
@Slf4j
public class CreateAuthorCommand extends Command<AuthorDtoRequest> {
    private final AuthorDtoRequest author;

    public CreateAuthorCommand(String name) {
        author = new AuthorDtoRequest(0L, name);
    }

    @Override
    public AuthorDtoRequest execute() {
        log.debug("method execute from CreateAuthorCommand class");
        return author;
    }
}
