package com.mjc.school.controller.command;

import lombok.extern.slf4j.Slf4j;

/**
 * Command implementation for retrieving news by ID.
 * This command encapsulates the news ID and returns it when executed.
 */
@Slf4j
public class GetNewsByIdCommand extends Command<Long>{
    private final Long id;

    public GetNewsByIdCommand(Long id) {
        System.out.println("CREATING CLASS" + id);
        this.id = id;
    }

    @Override
    public Long execute() {
        log.debug("method execute from GetNewsByIdCommand class");
        System.out.println("METHOD EXECUTE ID" +  id);
        return id;
    }
}
