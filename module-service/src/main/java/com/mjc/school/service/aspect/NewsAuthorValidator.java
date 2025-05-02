package com.mjc.school.service.aspect;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.exceptions.CheckException;
import com.mjc.school.service.exceptions.ErrorCodes;
import com.mjc.school.service.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Aspect for validating News and Author DTOs before service operations.
 *
 * Performs cross-cutting validation logic including:
 *
 *   Field length validation
 *   Null checks
 *   Author existence verification
 *
 */
@Slf4j
@Aspect
@Component
public class NewsAuthorValidator {
    private static final int TITLE_MIN = 5;
    private static final int TITLE_MAX = 30;
    private static final int CONTENT_FIELD_MIN = 5;
    private static final int CONTENT_FIELD_MAX = 255;
    private static final int AUTHOR_MIN = 3;
    private static final int AUTHOR_MAX = 15;
    private static final int TAG_NAME_MIN = 5;
    private static final int TAG_NAME_MAX = 30;
    @Autowired
    BaseRepository<AuthorModel, Long> repository;

    //@Pointcut("execution(* com.mjc.school.service.implementation.ImplementNewsService.create(..))")
    //public void cNews (){}

    /**
     * Validates News DTO before service operations.
     * @param newsDTO News data transfer object
     * @throws CheckException if validation fails
     */
    @Before("@annotation(com.mjc.school.service.annotations.Validate) " +
            "&& within(com.mjc.school.service.implementation.ImplementNewsService) " +
            "&& args(newsDTO)")
    //@Before("cNews() && args(newsDTO)")
    public void validateNews (NewsDtoRequest newsDTO){
        if (newsDTO == null) {
            log.error("News DTO is null");
            throw new CheckException("The field is null ");
        }
        validateString(newsDTO.getName(), "News title",  TITLE_MIN, TITLE_MAX);
        validateString(newsDTO.getContent(), "News content", CONTENT_FIELD_MIN, CONTENT_FIELD_MAX);
        checkAuthorId(newsDTO.getAuthorId());
    }

    /**
     * Validates Author DTO before service operations.
     * @param authorDTO Author data transfer object
     * @throws CheckException if validation fails
     */
    @Before("@annotation(com.mjc.school.service.annotations.Validate) " +
            "&& within(com.mjc.school.service.implementation.ImplementAuthorService) " +
            "&& args(authorDTO)")
    public void validateAuthor (AuthorDtoRequest authorDTO){
        if (authorDTO == null) {
            log.error("Author DTO is null");
            throw new CheckException("The field is null ");
        } else {
            validateString(authorDTO.getName(),"Author name", AUTHOR_MIN, AUTHOR_MAX);
        }
    }

    /**
     * Validates Tag DTO before service operations.
     * @param tagDTO Tag data transfer object
     * @throws CheckException if validation fails
     */
    @Before("@annotation(com.mjc.school.service.annotations.Validate) " +
            "&& within(com.mjc.school.service.implementation.ImplementTagService) " +
            "&& args(tagDTO)")
    public void validateTag (TagDtoRequest tagDTO){
        if (tagDTO == null) {
            log.error("Tag DTO is null");
            throw new CheckException("The field is null ");
        } else {
            validateString(tagDTO.getName(),"Tag name", TAG_NAME_MIN, TAG_NAME_MAX);
        }
    }

    /**
     * Validates string field length.
     * @param value Field value
     * @param fieldName Field name for error messages
     * @param min Minimum length
     * @param max Maximum length
     */
    private void validateString (String value, String fieldName, int min, int max) {
        if (value.length() < min || value.length() > max){
            log.error("Invalid {} length: {} (must be between {} and {})",fieldName, value.length(), min, max);
            throw new CheckException(String.format(ErrorCodes.STRING_LANGTH.toString(), fieldName, min, max, fieldName, value));
        }
    }

    /**
     * Validates author ID existence.
     * @param id Author ID
     * @throws NotFoundException if author doesn't exist
     */
    private void checkAuthorId (Long id){
        if(id == null) {
            throw new CheckException(String.format(ErrorCodes.NEGATIVE_OR_NULL_NUMBER.toString(), id));
        } else if (!repository.existById(id)){
            log.error("Author with ID {} not found", id);
            throw new NotFoundException(String.format(ErrorCodes.AUTH_ID_NOT_EXIST.toString(), id));
        }
    }
}
