package com.mjc.school.service.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing an author response.
 *
 * Contains the author data returned to clients after operations like:
 *
 *   GET /authors - Retrieve all authors
 *   GET /authors/{id} - Retrieve specific author
 *   POST/PUT /authors - Return created/updated author
 *
 *
 * Implements {@link DtoResponse} to maintain consistent response structure
 * across all API endpoints.
 */
@Data
public class AuthorDtoResponse implements DtoResponse {
    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
}
