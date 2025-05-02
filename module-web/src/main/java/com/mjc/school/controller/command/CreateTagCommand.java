package com.mjc.school.controller.command;

import com.mjc.school.service.dto.TagDtoRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Command implementation for creating a new tag.
 * This command encapsulates the tag creation request data and returns
 * the prepared TagDtoRequest when executed.
 */
@Slf4j
public class CreateTagCommand extends Command<TagDtoRequest>{
    private final TagDtoRequest tag;

    public CreateTagCommand(String name) {
        tag = new TagDtoRequest(0L, name);
    }

    @Override
    public TagDtoRequest execute() {
        log.debug("method execute from CreateTagCommand class");
        return tag;
    }
}
