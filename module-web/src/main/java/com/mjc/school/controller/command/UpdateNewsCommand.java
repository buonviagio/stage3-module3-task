package com.mjc.school.controller.command;

import com.mjc.school.service.dto.NewsDtoRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Command implementation for updating an existing news.
 * Encapsulates the author update operation including the author ID and new name.
 * Returns the {@link NewsDtoRequest} containing the update data when executed.
 */
@Slf4j
public class UpdateNewsCommand extends Command<NewsDtoRequest>{
    NewsDtoRequest n;

    public UpdateNewsCommand(Long newsId,String title, String content, Long authorId) {
        n = new NewsDtoRequest(newsId, title, content, authorId);
    }

    @Override
    public NewsDtoRequest execute() {
        log.debug("method execute from UpdateNewsCommand class");
        return n;
    }
}
