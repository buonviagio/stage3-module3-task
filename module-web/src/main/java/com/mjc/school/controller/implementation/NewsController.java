package com.mjc.school.controller.implementation;

import com.mjc.school.controller.annotations.CommandBody;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.annotations.CommandParam;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.implementation.ImplementNewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import java.util.List;
import java.util.Map;

/**
 * Controller for handling news-related operations.
 *
 * <p>Implements the command pattern through {@link CommandHandler} annotations
 * to route requests to appropriate service methods. Acts as the entry point
 * for news operations in the application.
 */
@Slf4j
@Component
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {

    private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;

    /**
     * Constructs the controller with required service dependency.
     * @param newsService Service layer for news operations
     */
    @Autowired
    public NewsController(BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService) {
        this.newsService = newsService;
        log.info("NewsController initialized with service: {}",
                newsService.getClass().getSimpleName());
    }

    /**
     * Retrieves all news articles.
     * @return List of all news DTOs
     */
    @CommandHandler(operation = "Get all news")
    @Override
    public List<NewsDtoResponse> readAll() {
        log.info("Controller news component -> Fetching all news articles");
        List<NewsDtoResponse> response = newsService.readAll();
        System.out.println(printNews(response.toArray(new NewsDtoResponse[0])));
        return response;
    }

    /**
     * Retrieves a specific news article by ID.
     * @param id News article identifier
     * @return News DTO response
     */
    @CommandHandler(operation = "Get news by id")
    @Override
    public NewsDtoResponse readById(@CommandParam("id") Long id) {
        log.info("Controller news component -> Fetching news article with ID: {}", id);
        System.out.println("WEB LEVEL ID " + id);
        NewsDtoResponse response = newsService.readById(id);
        System.out.println(printNews(response));
        return response;
    }

    /**
     * Creates a new news article.
     * @param createRequest News creation DTO
     * @return Created news DTO
     */
    @CommandHandler(operation = "Create news")
    @Override
    public NewsDtoResponse create(@CommandBody NewsDtoRequest createRequest) {
        log.info("Creating new news article with title: {}", createRequest.getName());
        NewsDtoResponse response = newsService.create(createRequest);
        System.out.println(printNews(response));
        return response;
    }

    /**
     * Updates an existing news article.
     * @param updateRequest News update DTO
     * @return Updated news DTO
     */
    @CommandHandler(operation = "Update news")
    @Override
    public NewsDtoResponse update(@CommandBody NewsDtoRequest updateRequest) {
        log.info("Updating news article with ID: {}", updateRequest.getId());
        NewsDtoResponse response = newsService.update(updateRequest);
        System.out.println(printNews(response));
        return response;
    }

    /**
     * Deletes a news article by ID.
     * @param id News article identifier
     * @return true if deletion was successful
     */
    @CommandHandler(operation = "Remove news by id")
    @Override
    public boolean deleteById(@CommandParam("id") Long id) {
        log.info("Attempting to delete news with ID: {}", id);
        return newsService.deleteById(id);
    }

    @CommandHandler(operation = "Get news by one parameter")
    public List<NewsDtoResponse> readNewsByParameters (@CommandBody Map map){
        log.info("Controller news component -> Fetching news by parameters");
        List<NewsDtoResponse> response = ((ImplementNewsService)newsService).readNewsByParameters(map);
        System.out.println(printNews(response.toArray(new NewsDtoResponse[0])));
        return response;
    }

    private String printNews(NewsDtoResponse... response) {
        StringBuilder builder = new StringBuilder();
        for (NewsDtoResponse n : response) {
            builder.append("NewsDtoResponse [id=").append(n.getId())
                    .append(", title=").append(n.getName())
                    .append(", content=").append(n.getContent())
                    .append(", createDate=").append(n.getCreateDate())
                    .append(", lastUpdatedDate=").append(n.getLastUpdateDate())
                    .append(", authorId=").append(n.getAuthorId()).append("]")
                    .append("\n");
        }
        return builder.toString();
    }
}
