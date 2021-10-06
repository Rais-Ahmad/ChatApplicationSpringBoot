package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Category;
import com.chatapplicationspringBoot.Model.Chat;
import com.chatapplicationspringBoot.Model.User;
import com.chatapplicationspringBoot.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listAllUser() {
        return userRepository.findAll();
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Adding a chat in an existing User
     * @param id
     * @param chats
     * @return
     */
    public User addNewChatById(Long id, List<Chat> chats) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        int size = chats.size();
        for (int i = 0; i < size; i++) {
            chats.get(i).setAnswerDate(dtf.format(now));
            chats.get(i).setQuestionDate(dtf.format(now));
        }

        User user1 = userRepository.findById(id).get();
        List<Chat> chat1 = user1.getChat();
        chat1.addAll(chats);
        userRepository.save(user1);
        return user1;
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Adding a new category in an existing User
     * @param id
     * @param categories
     * @return
     */
    public User addNewCategoryById(Long id, List<Category> categories) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        int size = categories.size();
        for (int i = 0; i < size; i++) {
            categories.get(i).setCategoryDate(dtf.format(now));

        }
        User user2 = userRepository.findById(id).get();
        Set<Category> category1 = user2.getCategory();
        category1.addAll(categories);
        userRepository.save(user2);
        return user2;
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Function to req the controller in order to  create a new user
     * @param user
     * @return
     */

    public Object saveUser(User user) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        int size = user.getChat().size();
        for (int i = 0; i < size; i++) {
            user.getChat().get(i).setAnswerDate(dtf.format(now));
            user.getChat().get(i).setQuestionDate(dtf.format(now));
        }
        try {
            return ResponseEntity.ok().body(userRepository.save(user));
        } catch (Exception e) {
            return new ResponseEntity("Unable to add User ", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> updateUser(User user) {
        try {
            userRepository.save(user);
            return new ResponseEntity<>("User has been successfully Updated", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User is not Updated", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Function to  get information of a particular user
     * @param id
     * @return
     */

    public User getUser(long id) {
        return userRepository.findById(id).get();
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Function to  delete a particular user
     * @param id
     */

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }


    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Function for matching Email
     * @param email
     * @return
     */

    public User getEmail(String email) {
        return userRepository.findByEmail(email);
    }

}