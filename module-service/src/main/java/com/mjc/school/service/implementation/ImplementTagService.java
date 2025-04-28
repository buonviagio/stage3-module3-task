package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.ModelMapper;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
        //log.info("Service Tag Component -> Fetching all tags articles");
        System.out.println("Method readALL from class Implementation Tags Service");
        List<TagModel> tagsList = tagRepository.readAll();

        //log.debug("Service Tags Component -> Retrieved {} tags  from repository", tagsList.size());

        return mapper.modelListToDtoListTags(tagsList);
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
}
