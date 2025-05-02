package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandBody;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.annotations.CommandParam;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.implementation.ImplementTagService;
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

    @CommandHandler(operation = "Get Tag by id")
    @Override
    public TagDtoResponse readById(@CommandParam("id")Long id) {
        log.info("Controller tag component -> Fetching tag with ID: {}", id);
        TagDtoResponse response = tagService.readById(id);
        System.out.println(printTags(response));
        return response;
    }

    @CommandHandler(operation = "Create Tag")
    @Override
    public TagDtoResponse create(@CommandBody TagDtoRequest createRequest) {
        log.info("Controller tag component -> Creating new tag with name: {}", createRequest.getName());
        TagDtoResponse response = tagService.create(createRequest);
        System.out.println(printTags(response));
        return response;
    }

    /**
     * Updates an existing tag.
     * @param updateRequest Tag update DTO
     * @return Updated tag DTO
     */
    @CommandHandler(operation = "Update tag")
    @Override
    public TagDtoResponse update(@CommandBody TagDtoRequest updateRequest) {
        log.info("Controller tag component -> Updating tag with ID: {}", updateRequest.getId());
        TagDtoResponse response = tagService.update(updateRequest);
        System.out.println(printTags(response));
        return response;
    }

    /**
     * Deletes an tag by ID.
     * @param id Tag identifier
     * @return true if deletion was successful
     */
    @CommandHandler(operation = "Remove tag by id")
    @Override
    public boolean deleteById(@CommandParam("id")Long id) {
        log.info("Controller tag component -> Deleting tag with ID: {}", id);
        boolean response = tagService.deleteById(id);
        System.out.println("Tag was deleted: " + response);
        return response;
    }

    @CommandHandler(operation = "Get tags by news id")
    public List<TagDtoResponse> readTagByNewsId(@CommandParam("id")Long id) {
        log.info("Controller tag component -> Fetching tag with news ID: {}", id);
        List<TagDtoResponse> response = ((ImplementTagService)tagService).readTagByNewsId(id);
        System.out.println(printTags(response.toArray(new TagDtoResponse[0])));
        return response;
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
