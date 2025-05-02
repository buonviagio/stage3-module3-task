package com.mjc.school.controller.command;

import lombok.extern.slf4j.Slf4j;

/**
 * Command implementation for deleting a tag by ID.
 * Encapsulates the tag deletion operation and returns the deleted tag's ID.
 */
@Slf4j
public class DeleteTagByIdCommand extends Command<Long>{
    private final Long id;

    public DeleteTagByIdCommand(Long id) {
        this.id = id;
    }

    @Override
    public Long execute() {
        log.debug("method execute from DeleteTagByIdCommand class");
        return id;
    }
}
