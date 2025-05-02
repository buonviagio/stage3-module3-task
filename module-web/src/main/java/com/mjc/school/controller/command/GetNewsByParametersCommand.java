package com.mjc.school.controller.command;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;


/**
 * Command implementation for retrieving news by Parameters.
 * This command encapsulates the news parameters and returns news when executed.
 */
@Slf4j
public class GetNewsByParametersCommand extends Command<Map<String, String>>{
    private final Map<String, String> map;

    public GetNewsByParametersCommand(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public Map<String, String> execute() {
        log.debug("method execute from GetNewsByParametersCommand class");
        return map;
    }
}
