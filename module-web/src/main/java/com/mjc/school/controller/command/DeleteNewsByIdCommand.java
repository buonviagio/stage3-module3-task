package com.mjc.school.controller.command;

import lombok.extern.slf4j.Slf4j;

/**
 * Command implementation for deleting news by ID.
 * Encapsulates the author deletion operation and returns the deleted author's ID.
 */
@Slf4j
public class DeleteNewsByIdCommand extends Command<Long>{
    private final Long id;

    public DeleteNewsByIdCommand(Long id) {
        this.id = id;
    }

    @Override
    public Long execute() {
        log.debug("method execute from DeleteNewsByIdCommand class");
        return id;
    }
}
