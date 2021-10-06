package com.chatapplicationspringBoot.Controller;

import com.chatapplicationspringBoot.Model.Category;
import com.chatapplicationspringBoot.Model.Chat;
import com.chatapplicationspringBoot.Model.User;
import com.chatapplicationspringBoot.Service.ChatService;
import com.chatapplicationspringBoot.Service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.NoSuchElementException;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@EnableSwagger2
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);
    private final ChatService chatService;
    String userLogout;
    final
    /**
     * @param userService
     * @Auther Rais Ahmad
     * @date 29/09/2021
     */
            UserService userService;
    private static final String defaultAuthValue = "da6d27f1-a033-44a9-88aa-a8a5f64a85db";
    private static boolean isLogin = false;

    public UserController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    public Boolean authorize(String authValue) {
        return defaultAuthValue.equals(authValue);

    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Comparing Email and password of user from database
     * @param paramEmail
     * @param paramPassword
     * @return
     */
    @GetMapping("/login")

    public ResponseEntity login(@RequestParam(value = "email") String paramEmail,
                                @RequestParam(value = "password") String paramPassword) {
        User user = userService.getEmail(paramEmail);

        if (paramEmail.equals(user.getEmail()) && paramPassword.equals(user.getPassword())) {
            isLogin = true;

            LOG.info(" Login performed by: " + user.getFirstName() + " !");
            userLogout = user.getFirstName();
            return new ResponseEntity("login successfully", HttpStatus.OK);
        } else {
            System.out.println(user.getEmail() + "  name is " + user.getFirstName() + "id is " + user.getId());
            return new ResponseEntity("Incorrect login details ", HttpStatus.NOT_FOUND);
        }

    }



    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Get All Users list
     * @return
     */
    @GetMapping("/allUsers")

    public ResponseEntity<Object> userList(@RequestHeader("authorization") String authValue) {

        /**
         * @Author Rais Ahmad
         * @Date 09-06-2021
         * @Discription login as well as header authorization will be checked here
         */

        if (authorize(authValue)) {
            List<User> userList = userService.listAllUser();
            LOG.info("List of users : " + userList );
            if (userList.isEmpty()) {
                return new ResponseEntity<>("No data available", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(userList, HttpStatus.OK);
            }
        } else
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Add a new User
     * @param authValue
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestHeader("Authorization") String authValue, @RequestBody User user) {

        if (authorize(authValue)) {
            userService.saveUser(user);
            LOG.info("User:  " + user + " added Successfully");
            return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);

        } else
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Get User by Id
     * @param authValue
     * @param id
     * @return
     */
    @GetMapping("/getUser/{id}")

    public ResponseEntity<Object> getUserById(@RequestHeader("Authorization") String authValue, @PathVariable Long id) {

        if (authorize(authValue)) {

            try {
                User user = userService.getUser(id);
                LOG.info("Get User by Id:  " + id);
                return new ResponseEntity<>(user, HttpStatus.CREATED);
            } catch (NoSuchElementException e) {
                LOGGER.error(e.getMessage(), e);
                return new ResponseEntity<>("User not found incorrect id ", HttpStatus.NOT_FOUND);
            }

        } else
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Update User
     * @param authValue
     * @param user
     * @return
     */
    @PutMapping("/updateUser")
    public ResponseEntity<Object> updateUser(@RequestHeader("authorization") String authValue, @RequestBody User user) {

        if (authorize(authValue)) {

            try {
                userService.updateUser(user);
                LOG.info("User updated successfully:  " + user);
                return new ResponseEntity<>("User updated successfully ", HttpStatus.OK);
            } catch (NoSuchElementException e) {
                LOGGER.error(e.getMessage(), e);
                return new ResponseEntity<>("User not found incorrect id ", HttpStatus.NOT_FOUND);
            }

        } else
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Add new Chat in existing User
     * @param id
     * @param chats
     * @return
     */

    @PostMapping("/addUser/newChat")

    public ResponseEntity<Object> addNewChatById(@RequestHeader("authorization") String authValue, @RequestHeader long id, @RequestBody List<Chat> chats) {

        if (authorize(authValue)) {
            try {
                User user = userService.addNewChatById(id, chats);
                LOG.info("New Chat added to User: " + id + " successfully!");
                return new ResponseEntity<>(user, HttpStatus.OK);
            } catch (NoSuchElementException e) {
                LOGGER.error(e.getMessage(), e);
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

        } else
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);

    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Add new Category in existing User
     * @param id
     * @param category
     * @return
     */

    @PostMapping("/addUser/newCategory")

    public ResponseEntity<Object> addNewCategoryById(@RequestHeader("authorization") String authValue, @RequestHeader long id, @RequestBody List<Category> category) {

        if (authorize(authValue)) {
            try {
                User user = userService.addNewCategoryById(id, category);
                LOG.info("New Category added to User: " + id + " successfully!");
                return new ResponseEntity<>(user, HttpStatus.OK);
            } catch (NoSuchElementException e) {
                LOGGER.error(e.getMessage(), e);
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } else
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Delete a User
     * @param id
     * @return
     */
    @DeleteMapping("deleteUser/{id}")

    public ResponseEntity<String> deleteUser(@RequestHeader("authorization") String authValue, @PathVariable Long id) {

        if (authorize(authValue)) {

            try {
                userService.deleteUser(id);
                LOG.info("User: " + id + " deleted successfully!");
                return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
            } catch (NoSuchElementException e) {
                LOGGER.error(e.getMessage(), e);
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

        } else
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
    }

}