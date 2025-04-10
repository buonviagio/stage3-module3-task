package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.ModelMapper;
import com.mjc.school.service.annotations.Validate;
import com.mjc.school.service.aspect.NewsAuthorValidator;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exceptions.ErrorCodes;
import com.mjc.school.service.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for author operations.
 *
 * Provides CRUD operations for authors, converting between:
 *
 *   Database models ({@link AuthorModel})
 *   Data transfer objects ({@link AuthorDtoRequest}/{@link AuthorDtoResponse})
 *
 *
 * Uses {@link ModelMapper} for object conversion and is validated by
 * {@link NewsAuthorValidator} aspect for method parameters.
 */
@Slf4j
@Service
public class ImplementAuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private final BaseRepository<AuthorModel, Long> repository;

    /**
     * Constructs the service with required dependencies.
     * @param repository Author repository for persistence operations
     */
    @Autowired
    public ImplementAuthorService(BaseRepository<AuthorModel, Long> repository) {
        this.repository = repository;
        log.debug("Service Author Component -> Author service initialized with repository: {}", repository.getClass().getSimpleName());
    }

    ModelMapper mapper = ModelMapper.INSTANCE;

    @Override
    public List<AuthorDtoResponse> readAll() {
        log.info("Service Author Component -> Fetching all authors");
        List<AuthorModel> authorList = repository.readAll();
        log.debug("Service Author Component -> Retrieved {} authors from repository", authorList.size());
        return mapper.modelListToDtoListAuthors(authorList);
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        log.info("Service Author Component -> Fetching author by ID: {}", id);
        Optional<AuthorModel> optional = repository.readById(id);
        if (repository.existById(id) && optional.isPresent()) {
            AuthorModel model = optional.get();
            return mapper.modelToDtoAuthor(model);
        } else {
            throw new NotFoundException(String.format(ErrorCodes.AUTH_ID_NOT_EXIST.toString(), id));
        }
    }

    @Validate
    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        log.info("Service Author Component -> Creating new author with request: {}", createRequest);
        AuthorModel authorModel = mapper.dtoToModelAuthor(createRequest);
        repository.create(authorModel);
        return mapper.modelToDtoAuthor(authorModel);
    }

    @Validate
    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        log.info("Service Author Component -> Updating author with ID: {}", updateRequest.getId());
        if (repository.existById(updateRequest.getId())) {
            AuthorModel model = mapper.dtoToModelAuthor(updateRequest);
            AuthorModel authorModel = repository.update(model);
            return mapper.modelToDtoAuthor(authorModel);
        } else {
            throw new NotFoundException(String.format(ErrorCodes.AUTH_ID_NOT_EXIST.toString(), updateRequest.getId()));
        }
    }

    @Override
    public boolean deleteById(Long id) {
        log.info("Service Author Component -> Deleting author with ID: {}", id);
        if (repository.deleteById(id)) {
            return true;
        } else {
            throw new NotFoundException(String.format(ErrorCodes.AUTH_ID_NOT_EXIST.toString(), id));
        }
    }

}
