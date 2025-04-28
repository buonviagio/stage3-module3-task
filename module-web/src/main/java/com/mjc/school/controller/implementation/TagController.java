package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TagController implements BaseController<TagDtoRequest, TagDtoResponse, Long> {

    private final BaseService<TagDtoRequest, TagDtoResponse, Long> tagService;

    @Autowired
    public TagController(BaseService<TagDtoRequest, TagDtoResponse, Long> tagService) {
        this.tagService = tagService;
        log.info("AuthorController initialized with service: {}",
                tagService.getClass().getSimpleName());
    }

    @CommandHandler(operation = "Get all tags")
    @Override
    public List<TagDtoResponse> readAll() {
        log.info("Controller tag component -> Fetching all tags");
        List<TagDtoResponse> responses = tagService.readAll();
        System.out.println(printTags(responses.toArray(new TagDtoResponse[0])));
        return responses;
    }

    @Override
    public TagDtoResponse readById(Long id) {
        return null;
    }

    @Override
    public TagDtoResponse create(TagDtoRequest createRequest) {
        return null;
    }

    @Override
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    private String printTags(TagDtoResponse... response) {
        StringBuilder builder = new StringBuilder();
        for (TagDtoResponse n : response) {
            builder.append("TagDtoResponse [id=").append(n.getId())
                    .append(", name=").append(n.getName())
                    .append(", createDate=").append(n.getCreateDate())
                    .append(", lastUpdatedDate=").append(n.getLastUpdateDate())
                    .append("]").append("\n");
        }
        return builder.toString();
    }
}
