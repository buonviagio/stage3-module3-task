package com.mjc.school.controller.command;

import lombok.extern.slf4j.Slf4j;

/**
 * Command implementation for deleting an author by ID.
 * Encapsulates the author deletion operation and returns the deleted author's ID.
 */
@Slf4j
public class DeleteAuthorByIdCommand extends Command<Long>{
    private final Long id;

    public DeleteAuthorByIdCommand(Long id) {
        this.id = id;
    }

    @Override
    public Long execute() {
        log.debug("method execute from DeleteAuthorByIdCommand class");
        return id;
    }
}
