package com.chatapplicationspringBoot.Controller;

import com.chatapplicationspringBoot.Model.User;
import com.chatapplicationspringBoot.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {

    final
    UserService userService;
    private static final String defaultAuthValue = "da6d27f1-a033-44a9-88aa-a8a5f64a85db";
    private static boolean isLogin = false;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    public Boolean authorize(String authValue) {
        return true;

        // return defaultAuthValue.equals(authValue);
    }

    @GetMapping("/login")

    // Comparing Email and password of user from database

    public ResponseEntity login(@RequestParam(value = "Email") String paramEmail,
                                @RequestParam(value = "password") String paramPassword) {
        User user = userService.getEmail(paramEmail);

        if (paramEmail.equals(user.getEmail()) && paramPassword.equals(user.getPassword())) {
            isLogin = true;
            return new ResponseEntity("login successfully", HttpStatus.OK);
        } else {
            System.out.println(user.getEmail() + "  name is " + user.getFirstName() + "id is " + user.getId());
            return new ResponseEntity("Incorrect login details ", HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("/logout")
    // Logout Functionality

    public ResponseEntity logout() {
        isLogin = false;
        return new ResponseEntity("User Logged out!", HttpStatus.OK);

    }

    @GetMapping(" ")

    // List of users will be displayed

    public ResponseEntity<Object> userList(/*@RequestHeader("Authorization") String authValue*/) {

        //  login as well as header authorization will be checked here

        if (isLogin) {
            if (authorize("true")) {
                List<User> userList = userService.listAllUser();
                // check if database is empty
                if (userList.isEmpty()) {
                    return new ResponseEntity<>("No data available", HttpStatus.NOT_FOUND);
                } else {
                    return new ResponseEntity<>(userList, HttpStatus.OK);
                }

            } else
                return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
        } else return new ResponseEntity<>("You are not logged in yet! ", HttpStatus.UNAUTHORIZED);
    }


    @PostMapping("/add")

    // Add a new user to database

    public ResponseEntity<String> addUser(@RequestHeader("Authorization") String authValue, @RequestBody User user) {


        if (isLogin) {
            if (authorize("true")) {

                userService.saveUser(user);
                return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);

            } else
                return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
        } else return new ResponseEntity<>("You are not logged in yet! ", HttpStatus.UNAUTHORIZED);
    }


    @GetMapping("/get/{id}")

    // Get user from database via providing user id

    public ResponseEntity<Object> get(@RequestHeader("Authorization") String authValue, @PathVariable Long id) {


        if (isLogin) {
            if (authorize("true")) {

                try {
                    User user = userService.getUser(id);
                    return new ResponseEntity<>(user, HttpStatus.CREATED);
                } catch (NoSuchElementException e) {
                    return new ResponseEntity<>("User not found incorrect id ", HttpStatus.NOT_FOUND);
                }

            } else
                return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
        } else return new ResponseEntity<>("You are not logged in yet! ", HttpStatus.UNAUTHORIZED);


    }

    @PutMapping("/update")

    // Update user information from database

    public ResponseEntity<Object> update(@RequestHeader("authorization") String authValue, @RequestBody User user) {


        if (isLogin) {
            if (authorize("true")) {

                try {
                    userService.saveUser(user);
                    return new ResponseEntity<>("User updated successfully ", HttpStatus.OK);
                } catch (NoSuchElementException e) {
                    return new ResponseEntity<>("User not found incorrect id ", HttpStatus.NOT_FOUND);
                }

            } else
                return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
        } else return new ResponseEntity<>("You are not logged in yet! ", HttpStatus.UNAUTHORIZED);


    }

    @DeleteMapping("delete/{id}")

    // Delete a particular user

    public ResponseEntity<String> delete(@PathVariable Long id) {


        if (isLogin) {
            if (authorize("true")) {

                try {
                    userService.deleteUser(id);
                    return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
                } catch (NoSuchElementException e) {
                    return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
                }

            } else
                return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
        } else return new ResponseEntity<>("You are not logged in yet! ", HttpStatus.UNAUTHORIZED);


    }

}