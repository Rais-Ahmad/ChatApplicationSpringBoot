package com.chatapplicationspringBoot.Model.PojoInterface;

import lombok.Data;

import java.util.List;
@Data
public class ThirdPartyDTO {

    private List<ChatDTO> dtoChats;
    private List<CategoryDTO> dtoCategories;

}
