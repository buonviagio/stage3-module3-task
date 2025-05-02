package com.mjc.school.controller.command;

import lombok.extern.slf4j.Slf4j;

/**
 * Command implementation for retrieving author by news ID.
 * This command encapsulates the news ID and returns Author with it when executed.
 */
@Slf4j
public class GetAuthorByNewsIdCommand  extends Command<Long> {
    private final Long id;

    public GetAuthorByNewsIdCommand(Long id) {
        this.id = id;
    }

    @Override
    public Long execute() {
        log.debug("method execute from GetAuthorByNewsIdCommand class");
        return id;
    }
}
