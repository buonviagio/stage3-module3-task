package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.implementation.NewsRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.ModelMapper;
import com.mjc.school.service.annotations.Validate;
import com.mjc.school.service.aspect.NewsAuthorValidator;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.ErrorCodes;
import com.mjc.school.service.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service implementation for news operations.
 *
 * Provides CRUD operations for news articles, handling:
 *
 *   Conversion between DTOs and domain models
 *   Delegating persistence operations to repository
 *   Business logic execution
 *
 *
 * Uses {@link ModelMapper} for object conversion and is validated by
 * {@link NewsAuthorValidator} aspect for method parameters.
 */
@Slf4j
@Service
public class ImplementNewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {

    private final BaseRepository<NewsModel, Long> newsRepository;
    private final BaseRepository<AuthorModel, Long> authorRepository;
    //ModelMapper mapper = ModelMapper.INSTANCE;
    private final ModelMapper mapper;
    /**
     * Constructs the service with required dependencies.
     * @param repository News repository for persistence operations
     */
    @Autowired
    public ImplementNewsService(BaseRepository<NewsModel, Long> repository, BaseRepository<AuthorModel, Long> authorRepository, ModelMapper mapper) {
        this.newsRepository = repository;
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    /**
     * Retrieves all news articles.
     * @return List of news DTOs
     */
    @Override
    public List<NewsDtoResponse> readAll() {
        log.info("Service News Component -> Fetching all news articles");
        List<NewsModel> newsList = newsRepository.readAll();
        log.debug("Service News Component -> Retrieved {} news articles from repository", newsList.size());
        return mapper.modelListToDtoListNews(newsList);
    }

    @Transactional
    @Override
    public NewsDtoResponse readById(Long id) {
        log.info("Service News Component -> Fetching news article with ID: {}", id);
        Optional<NewsModel> optional = newsRepository.readById(id);
        if (newsRepository.existById(id) && optional.isPresent()) {
            NewsModel model = optional.get();
            return mapper.modelToDtoNews(model);
        } else {
            throw new NotFoundException(String.format(ErrorCodes.NEW_ID_N_EXIST.toString(), id));
        }
    }

    @Transactional
    @Validate
    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        log.info("Service News Component -> Creating new news article. Title: {}", createRequest.getName());

        Optional<AuthorModel> author = authorRepository.readById(createRequest.getAuthorId());
        if(author.isEmpty()) {
            throw new NotFoundException(String.format(ErrorCodes.AUTH_ID_NOT_EXIST.toString(), createRequest.getAuthorId()));
        }
        NewsModel newsModel = mapper.dtoToModelNews(createRequest);
        newsModel.setAuthor(author.get());
        NewsModel result = newsRepository.create(newsModel);
        return mapper.modelToDtoNews(result);
    }

    @Transactional
    @Validate
    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        log.info("Service News Component -> Updating news article with ID: {}", updateRequest.getId());
        Optional<NewsModel> news = newsRepository.readById(updateRequest.getId());
        Optional<AuthorModel> author = authorRepository.readById(updateRequest.getAuthorId());
        if (news.isEmpty()){
            throw new NotFoundException(String.format(ErrorCodes.NEW_ID_N_EXIST.toString(), updateRequest.getId()));
        }else if (author.isEmpty()){
            throw new NotFoundException(String.format(ErrorCodes.AUTH_ID_NOT_EXIST.toString(), updateRequest.getAuthorId()));
        } else {
            NewsModel model = news.get();
            mapper.updateNewsModelFromDto(updateRequest, model, author.get());
            NewsModel newsModel = newsRepository.update(model);
            return mapper.modelToDtoNews(newsModel);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        log.info("Deleting news article with ID: {}", id);
        if (newsRepository.existById(id)){
            return newsRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format(ErrorCodes.NEW_ID_N_EXIST.toString(), id));
        }
    }

    public List<NewsDtoResponse> readNewsByParameters(Map map) {
        log.info("Service News Component -> Fetching news by parameters");
        String tag = map.get("tag").toString().charAt(0) == '0' ? null : String.valueOf(map.get("tag"));
        Long tagId = Long.parseLong((String)map.get("tag_id")) == 0 ? null : Long.parseLong((String) map.get("tag_id"));
        String author = map.get("author_name").toString().charAt(0) == '0' ? null : String.valueOf(map.get("author_name"));
        String title = map.get("title").toString().charAt(0) == '0' ? null : String.valueOf(map.get("title"));
        String content = map.get("content").toString().charAt(0) == '0' ? null : String.valueOf(map.get("content"));
        List<NewsModel> newsList = ((NewsRepository)newsRepository).findByCriteria(tag, tagId, author, title, content);
        return mapper.modelListToDtoListNews(newsList);
    }

}

