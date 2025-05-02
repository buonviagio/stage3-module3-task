package com.mjc.school.controller.command;

import lombok.extern.slf4j.Slf4j;

/**
 * Command implementation for retrieving tag by news ID.
 * This command encapsulates the news ID and returns tag by news id when executed.
 */
@Slf4j
public class GetTagByNewsIdCommand extends Command<Long>  {
    private final Long id;

    public GetTagByNewsIdCommand(Long id) {
        this.id = id;
    }

    @Override
    public Long execute() {
        log.debug("method execute from GetTagByNewsIdCommand class");
        return id;
    }
}
