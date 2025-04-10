package com.mjc.school.controller.command;

import com.mjc.school.service.dto.NewsDtoResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * A concrete command that retrieves all authors and returns them as a list of {@link NewsDtoResponse}.
 *
 * This command follows the Command Pattern and is responsible for encapsulating
 * the logic required to fetch all authors from a data source (e.g., database, API).
 */
@Slf4j
public class GetAllNewsCommand extends Command<List<NewsDtoResponse>>{

    @Override
    public List<NewsDtoResponse> execute() {
        log.debug("method execute from GetAllNewsCommand class");
        return new ArrayList<>();
    }
}
