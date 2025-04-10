package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
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

@Slf4j
@Service
public class ImplementTagService implements BaseService<TagDtoRequest, TagDtoResponse, Long> {

    private final BaseRepository<TagModel, Long> tagRepository;
    ModelMapper mapper = ModelMapper.INSTANCE;

    @Autowired
    public ImplementTagService(BaseRepository<TagModel, Long> repository) {
        this.tagRepository = repository;
    }

    @Override
    public List<TagDtoResponse> readAll() {
        return List.of();
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
