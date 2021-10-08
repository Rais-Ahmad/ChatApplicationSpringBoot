package com.chatapplicationspringBoot.Model.PojoInterface;

import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private String createdDate;
    private String updatedDate;
}
