package com.mjc.school.controller.command;

import com.mjc.school.service.dto.AuthorDtoRequest;
import lombok.extern.slf4j.Slf4j;
/**
 * Command implementation for updating an existing author.
 * Encapsulates the author update operation including the author ID and new name.
 * Returns the {@link AuthorDtoRequest} containing the update data when executed.
 */
@Slf4j
public class UpdateAuthorCommand extends Command<AuthorDtoRequest>{
    AuthorDtoRequest author;

    public UpdateAuthorCommand(Long authorId,String name) {
        author = new AuthorDtoRequest(authorId, name);
    }

    @Override
    public AuthorDtoRequest execute() {
        log.debug("method execute from UpdateAuthorCommand class");
        return author;
    }
}
