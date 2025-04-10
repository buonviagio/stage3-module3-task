package com.mjc.school.service.dto;

import lombok.Data;
/**
 * Data Transfer Object (DTO) for author-related requests.
 *
 * Represents the payload for creating or updating authors in the system.
 * Implements {@link DtoRequest} to ensure consistent request structure.
 */
// Lombok annotation generating getters, setters, toString(), equals(), and hashCode()
 @Data
public class AuthorDtoRequest implements DtoRequest{
    private Long id;
    private String name;

    public AuthorDtoRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
