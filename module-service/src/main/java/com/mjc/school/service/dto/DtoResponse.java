package com.mjc.school.service.dto;

import java.time.LocalDateTime;
/**
 * Represents a Data Transfer Object (DTO) for response payloads.
 *
 * This interface defines the common structure for all response DTOs in the system,
 * ensuring consistent fields for entity representations sent to clients. Implementations
 * should represent read-only views of domain entities.
 */
 public interface DtoResponse {
    /**
     * Gets the unique identifier of the entity.
     * @return The entity ID, or null if not persisted
     */
    Long getId();

    /**
     * Sets the unique identifier of the entity.
     * @param id The ID to assign to the DTO
     */
    void setId(Long id);

    /**
     * Gets the display name of the entity.
     * @return The name of the entity (never null)
     */
    String getName();

    /**
     * Sets the display name of the entity.
     * @param name The name to assign (must not be null)
     */
    void setName(String name);

    /**
     * Gets the creation timestamp of the entity.
     * @return The date/time when the entity was created (never null)
     */
    LocalDateTime getCreateDate();

    /**
     * Sets the creation timestamp of the entity.
     * @param createDate The creation date to assign (must not be null)
     */
    void setCreateDate(LocalDateTime createDate);

    /**
     * Gets the last modification timestamp of the entity.
     * @return The date/time when the entity was last updated
     */
    LocalDateTime getLastUpdateDate ();

    /**
     * Gets the last modification timestamp of the entity.
     * @return The date/time when the entity was last updated
     */
    void setLastUpdateDate(LocalDateTime lastUpdateDate);
}
