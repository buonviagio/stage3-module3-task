package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.implementation.AuthorRepository;
import com.mjc.school.repository.implementation.TagRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.ModelMapper;
import com.mjc.school.service.annotations.Validate;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.exceptions.ErrorCodes;
import com.mjc.school.service.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ImplementTagService implements BaseService<TagDtoRequest, TagDtoResponse, Long> {

    private final BaseRepository<TagModel, Long> tagRepository;
    //ModelMapper mapper = ModelMapper.INSTANCE;
    private final ModelMapper mapper;
    /**
     * Constructs the service with required dependencies.
     * @param repository News repository for persistence operations
     */
    @Autowired
    public ImplementTagService(BaseRepository<TagModel, Long> repository, ModelMapper mapper) {
        this.tagRepository = repository;
        this.mapper = mapper;
    }


    @Override
    public List<TagDtoResponse> readAll() {
        log.info("Service Tag Component -> Fetching all tags articles");
        System.out.println("Method readALL from class Implementation Tags Service");
        List<TagModel> tagsList = tagRepository.readAll();
        log.debug("Service Tags Component -> Retrieved {} tags  from repository", tagsList.size());
        return mapper.modelListToDtoListTags(tagsList);
    }

    @Override
    public TagDtoResponse readById(Long id) {
        log.info("Service Author Component -> Fetching author by ID: {}", id);
        Optional<TagModel> optional = tagRepository.readById(id);
        if (tagRepository.existById(id) && optional.isPresent()) {
            TagModel model = optional.get();
            return mapper.modelToDtoTag(model);
        } else {
            throw new NotFoundException(String.format(ErrorCodes.TAG_ID_NOT_EXIST.toString(), id));
        }
    }

    @Validate
    @Override
    @Transactional
    public TagDtoResponse create(TagDtoRequest createRequest) {
        log.info("Service Tag Component -> Creating new tag with request: {}", createRequest);
        TagModel tagModel = mapper.dtoToModelTag(createRequest);
        tagRepository.create(tagModel);
        return mapper.modelToDtoTag(tagModel);
    }

    @Validate
    @Override
    @Transactional
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        log.info("Service Tag Component -> Updating tag with ID: {}", updateRequest.getId());
        if (tagRepository.existById(updateRequest.getId())) {
            TagModel model = mapper.dtoToModelTag(updateRequest);
            TagModel tagModel = tagRepository.update(model);
            return mapper.modelToDtoTag(tagModel);
        } else {
            throw new NotFoundException(String.format(ErrorCodes.AUTH_ID_NOT_EXIST.toString(), updateRequest.getId()));
        }
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        log.info("Service Tag Component -> Deleting tag with ID: {}", id);
        if (tagRepository.deleteById(id)) {
            return true;
        } else {
            throw new NotFoundException(String.format(ErrorCodes.TAG_ID_NOT_EXIST.toString(), id));
        }
    }

    @Transactional
    public List<TagDtoResponse> readTagByNewsId(Long id) {
        log.info("Service Tag Component -> Fetching tag by news ID: {}", id);
        List<TagModel> tagModels = ((TagRepository) tagRepository).findTagByNewsId(id);
        System.out.println("Result from Service layer " + tagModels.size());
        if (!tagModels.isEmpty()) {
            return mapper.modelListToDtoListTags(tagModels);
        } else {
            throw new NotFoundException(String.format(ErrorCodes.NEW_ID_N_EXIST.toString(), id));
        }
    }
}
