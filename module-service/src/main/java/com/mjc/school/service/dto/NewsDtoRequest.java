package com.mjc.school.service.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for news-related requests.
 *
 * Represents the payload for creating or updating news in the system.
 * Implements {@link DtoRequest} to ensure consistent request structure.
 */
// Lombok annotation generating getters, setters, toString(), equals(), and hashCode()
@Data
public class NewsDtoRequest implements DtoRequest{
    private Long id;
    private String name;
    private String content;
    private Long authorId;

    public NewsDtoRequest(Long id, String name, String content, Long authorId) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }
    public String getContent() {
        return content;
    }
}
