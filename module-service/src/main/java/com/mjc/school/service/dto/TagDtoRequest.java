package com.mjc.school.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TagDtoRequest implements DtoRequest{
    private Long id;
    private String name;
}
