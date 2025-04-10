package com.mjc.school.controller.implementation;


import com.mjc.school.service.BaseService;
import com.mjc.school.controller.annotations.CommandBody;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.annotations.CommandParam;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mjc.school.controller.BaseController;
import java.util.List;

/**
 * Controller for handling author-related operations.
 *
 * <p>Implements the command pattern through {@link CommandHandler} annotations
 * to route requests to appropriate service methods. Acts as the entry point
 * for author operations in the application.
 */
@Slf4j
@Component
public class AuthorController implements BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> {

    private final BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService;

    /**
     * Constructs the controller with required service dependency.
     * @param authorService Service layer for author operations
     */
    @Autowired
    public AuthorController(BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService) {
        this.authorService = authorService;
        log.info("AuthorController initialized with service: {}",
                authorService.getClass().getSimpleName());
    }

    /**
     * Retrieves all authors.
     * @return List of all author DTOs
     */
    @CommandHandler(operation = "Get all authors")
    @Override
    public List<AuthorDtoResponse> readAll() {
        log.info("Controller author component -> Fetching all authors");
        List<AuthorDtoResponse> responses = authorService.readAll();
        System.out.println(printAuthor(responses.toArray(new AuthorDtoResponse[0])));
        return responses;
    }

    /**
     * Retrieves a specific author by ID.
     * @param id Author identifier
     * @return Author DTO response
     */
    @CommandHandler(operation = "Get author by id")
    @Override
    public AuthorDtoResponse readById(@CommandParam("id") Long id) {
        log.info("Controller author component -> Fetching author with ID: {}", id);
        AuthorDtoResponse response = authorService.readById(id);
        System.out.println(printAuthor(response));
        return response;
    }

    /**
     * Creates a new author.
     * @param createRequest Author creation DTO
     * @return Created author DTO
     */
    @CommandHandler(operation = "Create author")
    @Override
    public AuthorDtoResponse create(@CommandBody AuthorDtoRequest createRequest) {
        log.info("Controller author component -> Creating new author with name: {}", createRequest.getName());
        AuthorDtoResponse response = authorService.create(createRequest);
        System.out.println(printAuthor(response));
        return response;
    }

    /**
     * Updates an existing author.
     * @param updateRequest Author update DTO
     * @return Updated author DTO
     */
    @CommandHandler(operation = "Update authors")
    @Override
    public AuthorDtoResponse update(@CommandBody AuthorDtoRequest updateRequest) {
        log.info("Controller author component -> Updating author with ID: {}", updateRequest.getId());
        AuthorDtoResponse response = authorService.update(updateRequest);
        System.out.println(printAuthor(response));
        return response;
    }

    /**
     * Deletes an author by ID.
     * @param id Author identifier
     * @return true if deletion was successful
     */
    @CommandHandler(operation = "Remove author by id")
    @Override
    public boolean deleteById(@CommandParam("id") Long id) {
        log.info("Controller author component -> Deleting author with ID: {}", id);
        boolean response = authorService.deleteById(id);
        System.out.println("Author was deleted: " + response);
        return response;
    }

    private String printAuthor(AuthorDtoResponse... response) {
        StringBuilder builder = new StringBuilder();
        for (AuthorDtoResponse n : response) {
            builder.append("AuthorDtoResponse [id=").append(n.getId())
                    .append(", name=").append(n.getName())
                    .append(", createDate=").append(n.getCreateDate())
                    .append(", lastUpdatedDate=").append(n.getLastUpdateDate())
                    .append("]").append("\n");
        }
        return builder.toString();
    }
}
