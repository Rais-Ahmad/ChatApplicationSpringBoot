package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Category;
import com.chatapplicationspringBoot.Model.Chat;
import com.chatapplicationspringBoot.Model.User;
import com.chatapplicationspringBoot.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    Chat chat;

    // Function for persistent layer

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Function to req the controller in order to  get information of all users

    public List<User> listAllUser() {
        return userRepository.findAll();
    }


    public User AddNewChatById(Long id, List<Chat> chats) {
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        int size = chats.size();
        for (int i = 0; i < size; i++) {
            chats.get(i).setAnswerDate(date);
            chats.get(i).setQuestionDate(date);
        }

        User user1 = userRepository.findById(id).get();
        List<Chat> chat1 = user1.getChat();
        chat1.addAll(chats);
        userRepository.save(user1);
        return user1;
    }

    public User AddNewCategoryById(Long id, List<Category> categories) {


        User user2 = userRepository.findById(id).get();
        Set<Category> category1 = user2.getCategory();
        category1.addAll(categories);
        userRepository.save(user2);
        return user2;
    }


    // Function to req the controller in order to  create a new user

    public Object saveUser(User user) {
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        int size = user.getChat().size();
        for (int i = 0; i < size; i++) {
            user.getChat().get(i).setAnswerDate(date);
            user.getChat().get(i).setQuestionDate(date);
        }
        try {
            return ResponseEntity.ok().body(userRepository.save(user));
        } catch (Exception e) {
            return new ResponseEntity("Unable to add User ", HttpStatus.BAD_REQUEST);
        }
    }


    // Function to req the controller in order to  get information of a particular user

    public User getUser(long id) {
        return userRepository.findById(id).get();
    }

    // Function to req the controller in order to  delete a particular user

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    // Function to req the Repository for matching credentials

    public User getEmail(String email) {
        return userRepository.findByEmail(email);
    }


}