package com.mjc.school.controller.command;

import com.mjc.school.service.dto.NewsDtoRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Command implementation for creating a new.
 * This command encapsulates the author creation request data and returns
 * the prepared NewsDtoRequest when executed.
 */
@Slf4j
public class CreateNewsCommand extends Command<NewsDtoRequest>{

    NewsDtoRequest n;

    public CreateNewsCommand(String title, String content, Long authorId) {
        n = new NewsDtoRequest(null, title, content, authorId);
    }

    @Override
    public NewsDtoRequest execute() {
        log.debug("method execute from CreateAuthorCommand class");
        return n;
    }
}
