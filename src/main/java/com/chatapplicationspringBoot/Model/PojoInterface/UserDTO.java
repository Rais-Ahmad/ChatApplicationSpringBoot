package com.chatapplicationspringBoot.Model.PojoInterface;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

   private long id;
   private int age;
   private String userName;
   private String email;
   private String password;
   private String cnic;
   private String createdDate;
   private String updatedDate;
   private List<ChatDTO> chats;
   private List<CategoryDTO> categories ;



}
