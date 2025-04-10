package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.implementation.AuthorRepository;
import com.mjc.school.repository.implementation.NewsRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.ModelMapper;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ImplementNewsServiceTest {

    private NewsRepository newsRepository = Mockito.mock(NewsRepository.class);

    private AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);

    private ModelMapper mapper = Mockito.mock(ModelMapper.class);

    @InjectMocks
    private ImplementNewsService newsService = new ImplementNewsService(newsRepository, authorRepository, mapper);;

    @Test
    void shouldCreateNewsSuccessfully_whenAuthorExists() {
        // Arrange-Act-Assert (AAA) Pattern for structuring unit test
        // given - this section sets up the necessary preconditions for the test.
        Long authorId = 1L;
        NewsDtoRequest dtoRequest = new NewsDtoRequest(null, "Test Title", "Test Content", authorId);

        AuthorModel author = new AuthorModel();
        author.setId(authorId);
        author.setName("Test Author");
        System.out.println("AUTHOR " + author);

        NewsModel newsModel = new NewsModel();
        newsModel.setTitle("Test Title");
        newsModel.setContent("Test Content");

        NewsModel savedNews = new NewsModel();
        savedNews.setId(100L);
        savedNews.setTitle("Test Title");
        savedNews.setContent("Test Content");
        savedNews.setAuthor(author);

        NewsDtoResponse expectedDto = new NewsDtoResponse();
        expectedDto.setId(100L);
        expectedDto.setName("Test Title");
        expectedDto.setContent("Test Content");
        expectedDto.setAuthorId(authorId);

        // when - this performs action which must be tested

        // mock.when - define how the mock objects should behave when their methods are called
        Mockito.when(authorRepository.readById(authorId)).thenReturn(Optional.of(author));
        //System.out.println("authorRepository.readById: " + authorRepository.readById(authorId));
        Mockito.when(mapper.dtoToModelNews(dtoRequest)).thenReturn(newsModel);
        Mockito.when(newsRepository.create(newsModel)).thenReturn(savedNews);
        Mockito.when(mapper.modelToDtoNews(savedNews)).thenReturn(expectedDto);

        // then - this verifying that the action is performed produced the expected outcome

        NewsDtoResponse result = newsService.create(dtoRequest);

        Assertions.assertThat(result)
                .isNotNull()
                .extracting("id", "name", "content", "authorId")
                .containsExactly(100L, "Test Title", "Test Content", authorId);
    }

    @Test
    void shouldUpdateNewsSuccessfully_whenNewsAndAuthorExist() {
        // Arrange
        Long newsId = 1L;
        Long authorId = 2L;
        NewsDtoRequest updateRequest = new NewsDtoRequest(newsId, "Updated Title", "Updated Content", authorId);

        NewsModel existingNews = new NewsModel();
        existingNews.setId(newsId);
        existingNews.setTitle("Original Title");
        existingNews.setContent("Original Content");

        AuthorModel existingAuthor = new AuthorModel();
        existingAuthor.setId(authorId);
        existingAuthor.setName("Test Author");

        NewsModel updatedNews = new NewsModel();
        updatedNews.setId(newsId);
        updatedNews.setTitle("Updated Title");
        updatedNews.setContent("Updated Content");
        updatedNews.setAuthor(existingAuthor);

        NewsDtoResponse expectedResponse = new NewsDtoResponse();
        expectedResponse.setId(newsId);
        expectedResponse.setName("Updated Title");
        expectedResponse.setContent("Updated Content");
        expectedResponse.setAuthorId(authorId);

        Mockito.when(newsRepository.readById(newsId)).thenReturn(Optional.of(existingNews));
        Mockito.when(authorRepository.readById(authorId)).thenReturn(Optional.of(existingAuthor));
        Mockito.doNothing().when(mapper).updateNewsModelFromDto(updateRequest, existingNews, existingAuthor);
        Mockito.when(newsRepository.update(existingNews)).thenReturn(updatedNews);
        Mockito.when(mapper.modelToDtoNews(updatedNews)).thenReturn(expectedResponse);

        // Act
        NewsDtoResponse actualResponse = newsService.update(updateRequest);

        // Assert
        Assertions.assertThat(actualResponse)
                .isNotNull()
                .extracting("id", "name", "content", "authorId")
                .containsExactly(newsId, "Updated Title", "Updated Content", authorId);

        verify(newsRepository, times(1)).readById(newsId);
        verify(authorRepository, times(1)).readById(authorId);
        verify(mapper, times(1)).updateNewsModelFromDto(updateRequest, existingNews, existingAuthor);
        verify(newsRepository, times(1)).update(existingNews);
        verify(mapper, times(1)).modelToDtoNews(updatedNews);
    }

    @Test
    void shouldThrowNotFoundException_whenAuthorDoesNotExist() {
        // Arrange
        Long newsId = 1L;
        Long authorId = 2L;
        NewsDtoRequest updateRequest = new NewsDtoRequest(newsId, "Updated Title", "Updated Content", authorId);

        NewsModel existingNews = new NewsModel();
        existingNews.setId(newsId);

        Mockito.when(newsRepository.readById(newsId)).thenReturn(Optional.of(existingNews));
        Mockito.when(authorRepository.readById(authorId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> newsService.update(updateRequest),
                String.format("ERROR_CODE: 000002 ERROR_MESSAGE: Author Id does not exist. Author Id is: %d", authorId));

        verify(newsRepository, times(1)).readById(newsId);
        verify(authorRepository, times(1)).readById(authorId);
        verify(mapper, never()).updateNewsModelFromDto(any(), any(), any());
        verify(newsRepository, never()).update(any());
        verify(mapper, never()).modelToDtoNews(any());
    }
}
