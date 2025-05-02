package com.mjc.school.service;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.dto.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * MapStruct Mapper interface for converting between domain models and DTOs.
 *
 * <p>Provides bidirectional mapping between:
 * <ul>
 *   <li>{@link NewsModel} ↔ {@link NewsDtoRequest}/{@link NewsDtoResponse}</li>
 *   <li>{@link AuthorModel} ↔ {@link AuthorDtoRequest}/{@link AuthorDtoResponse}</li>
 * </ul>
 *
 * <p>Generated implementation automatically handles field mapping when names match.
 * Custom mappings are specified where field names differ or special handling is required.
 */
//@Mapper
@Mapper(componentModel = "spring")
public interface ModelMapper {

    /**
     * Singleton instance of the mapper.
     * MapStruct will generate the implementation at compile time.
     */
    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

    /**
     * Converts a list of News models to News DTO responses.
     * @param newsModelList List of news domain models
     * @return List of news DTO responses
     */
    @Mapping(source = "title", target = "name")
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(target = "author.getNews()", ignore = true) //
    List<NewsDtoResponse> modelListToDtoListNews(List<NewsModel> newsModelList);

    /**
     * Converts a list of Author models to Author DTO responses.
     * @param authorModelList List of author domain models
     * @return List of author DTO responses
     */
    List<AuthorDtoResponse> modelListToDtoListAuthors(List<AuthorModel> authorModelList);

    /**
     * Converts a single News model to News DTO response.
     * @param baseEntity News domain model
     * @return News DTO response
     */
    @Mapping(source = "title", target = "name")
    //@Mapping(source = "author.id", target = "authorId")
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "author.news", ignore = true)
/*    @Mapping(target = "createDate", source = "baseEntity.createDate")
    @Mapping(target = "lastUpdateDate", source = "baseEntity.lastUpdateDate")
    @Mapping(target = "id", source = "baseEntity.id")*/
    NewsDtoResponse modelToDtoNews(NewsModel baseEntity);

    /**
     * Converts a single Author model to Author DTO response.
     * @param baseEntity Author domain model
     * @return Author DTO response
     */
    AuthorDtoResponse modelToDtoAuthor(AuthorModel baseEntity);

    /**
     * Converts News DTO request to News model.
     * @param dtoRequest News DTO request
     * @return News domain model
     * @mapping Ignore createDate and lastUpdateDate fields (will be set by persistence layer)
     */
   /* @Mappings({
            @Mapping(target = "createDate", ignore = true),
            @Mapping(target = "lastUpdateDate", ignore = true)
    })*/
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "title", source = "name")
    @Mapping(target = "content", source = "content")
    NewsModel dtoToModelNews(NewsDtoRequest dtoRequest);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "title", source = "name")
    @Mapping(target = "content", source = "content")
    NewsModel dtoToModelNewsForUpdateAction(NewsDtoRequest dtoRequest);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "title", source = "dtoRequest.name")
    @Mapping(target = "content", source = "dtoRequest.content")
    @Mapping(target = "author", source = "author")
    void updateNewsModelFromDto(NewsDtoRequest dtoRequest, @MappingTarget NewsModel newsModel, AuthorModel author);

    /**
     * Converts Author DTO request to Author model.
     * @param dtoRequest Author DTO request
     * @return Author domain model
     * @mapping Ignore createDate and lastUpdateDate fields (will be set by persistence layer)
     */
    @Mappings({
            @Mapping(target = "createDate", ignore = true),
            @Mapping(target = "lastUpdateDate", ignore = true)
    })
    AuthorModel dtoToModelAuthor(AuthorDtoRequest dtoRequest);


    // Tag Mapper
    @Mapping(source = "name", target = "name")
    List<TagDtoResponse> modelListToDtoListTags(List<TagModel> tagsModelList);

    TagDtoResponse modelToDtoTag(TagModel baseEntity);


    @Mappings({
            @Mapping(target = "createDate", ignore = true),
            @Mapping(target = "lastUpdateDate", ignore = true)
    })
    TagModel dtoToModelTag(TagDtoRequest dtoRequest);

    //TagDtoResponse modelToDtoTag(TagModel baseEntity);
}

