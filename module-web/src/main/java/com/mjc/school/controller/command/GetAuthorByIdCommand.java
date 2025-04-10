package com.mjc.school.controller.command;

import lombok.extern.slf4j.Slf4j;

/**
 * Command implementation for retrieving author by ID.
 * This command encapsulates the news ID and returns it when executed.
 */
@Slf4j
public class GetAuthorByIdCommand  extends Command<Long>{

    private final Long id;

    public GetAuthorByIdCommand(Long id) {
        this.id = id;
    }

    @Override
    public Long execute() {
        log.debug("method execute from GetAuthorByIdCommand class");
        return id;
    }
}

