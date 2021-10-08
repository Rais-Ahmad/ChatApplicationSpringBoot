package com.chatapplicationspringBoot.Model.PojoInterface;

import com.chatapplicationspringBoot.Model.Category;
import com.chatapplicationspringBoot.Model.Chat;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserChatCategory {
    private List<Chat> chats;
    private Set<Category> categories;

}
