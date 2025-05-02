package com.mjc.school.controller.command;

import lombok.extern.slf4j.Slf4j;

/**
 * Command implementation for retrieving tag by ID.
 * This command encapsulates the news ID and returns it when executed.
 */
@Slf4j
public class GetTagByIdCommand extends Command<Long> {
    private final Long id;

    public GetTagByIdCommand(Long id) {
        this.id = id;
    }

    @Override
    public Long execute() {
        log.debug("method execute from GetTagByIdCommand class");
        return id;
    }
}
