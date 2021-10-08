package com.chatapplicationspringBoot.PojoInterface;

import lombok.Data;

import java.util.List;

@Data
public abstract class UserPojoDTO {

   private long id;
   private int age;
   private String userName;
   private String email;
   private String password;
   private String cnic;
   private String createdDate;
   private String updatedDate;
   private List<String> chats;
   private List<String> categories;



}
