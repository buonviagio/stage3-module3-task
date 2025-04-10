package com.mjc.school.service.dto;

import lombok.Data;

@Data
public class TagDtoRequest implements DtoRequest{
    private Long id;
    private String name;
}
