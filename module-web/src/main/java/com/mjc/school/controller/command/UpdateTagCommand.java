package com.mjc.school.controller.command;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.TagDtoRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Command implementation for updating an existing tag.
 * Encapsulates the tag update operation including the tag ID and new name.
 * Returns the {@link TagDtoRequest} containing the update data when executed.
 */
@Slf4j
public class UpdateTagCommand extends Command<TagDtoRequest> {
    TagDtoRequest tag;

    public UpdateTagCommand(Long authorId,String name) {
        tag = new TagDtoRequest(authorId, name);
    }

    @Override
    public TagDtoRequest execute() {
        log.debug("method execute from UpdateAuthorCommand class");
        return tag;
    }
}
