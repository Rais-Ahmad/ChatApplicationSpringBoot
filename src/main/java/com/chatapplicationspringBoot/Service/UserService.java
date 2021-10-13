package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Category;
import com.chatapplicationspringBoot.Model.Chat;
import com.chatapplicationspringBoot.Model.PojoInterface.SMS;
import com.chatapplicationspringBoot.Model.PojoInterface.ThirdPartyDTO;
import com.chatapplicationspringBoot.Model.PojoInterface.UserChatCategory;
import com.chatapplicationspringBoot.Model.PojoInterface.UserDTO;
import com.chatapplicationspringBoot.Model.User;
import com.chatapplicationspringBoot.Repository.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final String ACCOUNT_SID = "AC31b2c9f66d33e1256230d66f8eb72516";

    private final String AUTH_TOKEN = "90effa9d3b6ae9dd1bd7a4244777c79c";

    private final String FROM_NUMBER = "+14135531059";
    URI uri;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    HttpHeaders headers = new HttpHeaders();

    public List<User> listAllUser() {
        return userRepository.findAll();
    }

    /**
     * @param id
     * @param chats
     * @return
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Adding a chat in an existing User
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
     * @param id
     * @param categories
     * @return
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Adding a new category in an existing User
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
     * @param user
     * @return
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Function to req the controller in order to  create a new user
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
            return new ResponseEntity("User already exist ", HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<Object> updateUser(User user) {
        try {
            userRepository.save(user);
            return new ResponseEntity<>("User has been successfully Updated", HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User is not Updated", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param id
     * @return
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Function to  get information of a particular user
     */

    public User getUser(long id) {
        return userRepository.findById(id).get();
    }

    /**
     * @param id
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Function to  delete a particular user
     */

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }


    /**
     * @param email
     * @return
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Function for matching Email
     */

    public User getEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public ResponseEntity<Object> getChatByUserId(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserChatCategory userChatCategory = new UserChatCategory();
            userChatCategory.setChats(user.get().getChat());
            userChatCategory.setCategories(user.get().getCategory());
            return ResponseEntity.ok().body(userChatCategory);
        } else if (!user.isPresent()) {
            final String baseUrl = "http://192.168.10.15:8080/user/get/";
            try {
                RestTemplate restTemplate = new RestTemplate();

                ResponseEntity<UserDTO> otherUser;
                uri = new URI(baseUrl + id);
                headers.set("Authorization", "user12345");
                HttpEntity<UserDTO> requestEntity = new HttpEntity<>(null, headers);
                otherUser = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, UserDTO.class);
                ThirdPartyDTO thirdPartyDTO = new ThirdPartyDTO();
                thirdPartyDTO.setDtoChats(otherUser.getBody().getChats());
                thirdPartyDTO.setDtoCategories(otherUser.getBody().getCategories());
                return new ResponseEntity<>(thirdPartyDTO , HttpStatus.FOUND);

               // return new ResponseEntity<>(otherUser.getBody(), HttpStatus.FOUND);



            } catch (HttpClientErrorException ex) {
                return new ResponseEntity("Missing request headers for other user api", HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                return new ResponseEntity("eeeeeeeeeeee", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }





    public ResponseEntity<Object> SendSms(long id, SMS sms){
        try{
            User user = userRepository.getById(id);
            if(null==user){
                return new ResponseEntity<>("There is no user exists against this id",HttpStatus.BAD_REQUEST);
            }
            else{
                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                Message message = Message.creator(new PhoneNumber(user.getPhone()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                        .create();
                System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction
                return new ResponseEntity<>("The message has been successfully sent to: "+user.getFirstName(),HttpStatus.OK);
            }
        }catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

