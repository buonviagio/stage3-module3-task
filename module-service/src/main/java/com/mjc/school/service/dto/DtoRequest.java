package com.mjc.school.service.dto;

/**
 * Defines the common structure for all request DTOs in the system, ensuring consistent
 * fields for data received from clients. Implementations should represent write operations
 * for domain entities.
 */
public interface DtoRequest {
    /**
     * Gets the unique identifier of the entity.
     *
     * @return The entity ID for updates, or null for new entity creation
     */
    Long getId();

    /**
     * Sets the unique identifier of the entity.
     *
     * @param id The ID to assign to the DTO.
     */
    void setId(Long id);


    /**
     * Gets the name of the entity.
     *
     * @return The current name value
     */
    String getName();
    /**
     * Sets the name of the entity.
     *
     * @param name The name to assign. Implementations should:
     *             - Validate for null/empty values
     *             - Trim whitespace if needed
     *             - Enforce length constraints
     *
     */
    void setName(String name);
}
