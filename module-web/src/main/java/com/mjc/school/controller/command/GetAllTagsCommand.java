package com.mjc.school.controller.command;

import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.dto.TagDtoResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GetAllTagsCommand extends Command<List<TagDtoResponse>>{
    @Override
    public List<TagDtoResponse> execute() {
        log.debug("method execute from GetAllTagsCommand class");
        return new ArrayList<>();
    }
}
