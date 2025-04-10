package com.mjc.school.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing news response.
 *
 * Contains the news data returned to clients after operations like:
 *
 *   GET /news - Retrieve all news
 *   GET /news/{id} - Retrieve specific news
 *   POST/PUT /news - Return created/updated news
 *
 *
 * Implements {@link DtoResponse} to maintain consistent response structure
 * across all API endpoints.
 */
@Data
public class NewsDtoResponse implements DtoResponse{
    private Long id;
    private String name;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
/*public String getContent() {
        return content;
    }*/
}
