package com.mjc.school.service;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.dto.TagDtoResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-28T19:43:44+0200",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.2.jar, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class ModelMapperImpl implements ModelMapper {

    @Override
    public List<NewsDtoResponse> modelListToDtoListNews(List<NewsModel> newsModelList) {
        if ( newsModelList == null ) {
            return null;
        }

        List<NewsDtoResponse> list = new ArrayList<NewsDtoResponse>( newsModelList.size() );
        for ( NewsModel newsModel : newsModelList ) {
            list.add( modelToDtoNews( newsModel ) );
        }

        return list;
    }

    @Override
    public List<AuthorDtoResponse> modelListToDtoListAuthors(List<AuthorModel> authorModelList) {
        if ( authorModelList == null ) {
            return null;
        }

        List<AuthorDtoResponse> list = new ArrayList<AuthorDtoResponse>( authorModelList.size() );
        for ( AuthorModel authorModel : authorModelList ) {
            list.add( modelToDtoAuthor( authorModel ) );
        }

        return list;
    }

    @Override
    public NewsDtoResponse modelToDtoNews(NewsModel baseEntity) {
        if ( baseEntity == null ) {
            return null;
        }

        NewsDtoResponse newsDtoResponse = new NewsDtoResponse();

        newsDtoResponse.setName( baseEntity.getTitle() );
        newsDtoResponse.setAuthorId( baseEntityAuthorId( baseEntity ) );
        newsDtoResponse.setId( baseEntity.getId() );
        newsDtoResponse.setContent( baseEntity.getContent() );
        newsDtoResponse.setCreateDate( baseEntity.getCreateDate() );
        newsDtoResponse.setLastUpdateDate( baseEntity.getLastUpdateDate() );

        return newsDtoResponse;
    }

    @Override
    public AuthorDtoResponse modelToDtoAuthor(AuthorModel baseEntity) {
        if ( baseEntity == null ) {
            return null;
        }

        AuthorDtoResponse authorDtoResponse = new AuthorDtoResponse();

        authorDtoResponse.setId( baseEntity.getId() );
        authorDtoResponse.setName( baseEntity.getName() );
        authorDtoResponse.setCreateDate( baseEntity.getCreateDate() );
        authorDtoResponse.setLastUpdateDate( baseEntity.getLastUpdateDate() );

        return authorDtoResponse;
    }

    @Override
    public NewsModel dtoToModelNews(NewsDtoRequest dtoRequest) {
        if ( dtoRequest == null ) {
            return null;
        }

        NewsModel newsModel = new NewsModel();

        newsModel.setTitle( dtoRequest.getName() );
        newsModel.setContent( dtoRequest.getContent() );

        return newsModel;
    }

    @Override
    public NewsModel dtoToModelNewsForUpdateAction(NewsDtoRequest dtoRequest) {
        if ( dtoRequest == null ) {
            return null;
        }

        NewsModel newsModel = new NewsModel();

        newsModel.setId( dtoRequest.getId() );
        newsModel.setTitle( dtoRequest.getName() );
        newsModel.setContent( dtoRequest.getContent() );

        return newsModel;
    }

    @Override
    public void updateNewsModelFromDto(NewsDtoRequest dtoRequest, NewsModel newsModel, AuthorModel author) {
        if ( dtoRequest == null && author == null ) {
            return;
        }

        if ( dtoRequest != null ) {
            newsModel.setTitle( dtoRequest.getName() );
            newsModel.setContent( dtoRequest.getContent() );
        }
        if ( author != null ) {
            newsModel.setAuthor( author );
        }
    }

    @Override
    public AuthorModel dtoToModelAuthor(AuthorDtoRequest dtoRequest) {
        if ( dtoRequest == null ) {
            return null;
        }

        AuthorModel authorModel = new AuthorModel();

        authorModel.setId( dtoRequest.getId() );
        authorModel.setName( dtoRequest.getName() );

        return authorModel;
    }

    @Override
    public List<TagDtoResponse> modelListToDtoListTags(List<TagModel> tagsModelList) {
        if ( tagsModelList == null ) {
            return null;
        }

        List<TagDtoResponse> list = new ArrayList<TagDtoResponse>( tagsModelList.size() );
        for ( TagModel tagModel : tagsModelList ) {
            list.add( tagModelToTagDtoResponse( tagModel ) );
        }

        return list;
    }

    private Long baseEntityAuthorId(NewsModel newsModel) {
        if ( newsModel == null ) {
            return null;
        }
        AuthorModel author = newsModel.getAuthor();
        if ( author == null ) {
            return null;
        }
        Long id = author.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected TagDtoResponse tagModelToTagDtoResponse(TagModel tagModel) {
        if ( tagModel == null ) {
            return null;
        }

        TagDtoResponse tagDtoResponse = new TagDtoResponse();

        tagDtoResponse.setId( tagModel.getId() );
        tagDtoResponse.setName( tagModel.getName() );
        tagDtoResponse.setCreateDate( tagModel.getCreateDate() );
        tagDtoResponse.setLastUpdateDate( tagModel.getLastUpdateDate() );

        return tagDtoResponse;
    }
}
