package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.User;
import com.chatapplicationspringBoot.Repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    // Function for persistent layer

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Function to req the controller in order to  get information of all users

    public List<User> listAllUser() {
        return userRepository.findAll();
    }



    // Function to req the controller in order to  create a new user

    public void saveUser(User user) {
        userRepository.save(user);
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

    public User getEmail(String email){
        return userRepository.findByEmail(email);
    }



}