package com.chatapplicationspringBoot.Model.PojoInterface;

import lombok.Data;

@Data
public class ChatDTO {

    private long id;
    private String answer;
    private String createdDate;
    private String question;
    private String updatedDate;

}
