package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Chat;
import com.chatapplicationspringBoot.Repository.ChatRepository;
import com.chatapplicationspringBoot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChatService {
    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }
@Autowired
private UserRepository userRepository;
    // Function to display the list of all chats

    public List<Chat> listAllUser() {
        return chatRepository.findAll();
    }

    public List<Chat> Listallchatbyuserid(Long userID) {
        return chatRepository.findByUserId(userID);
    }
    public Chat createuserchat(Long userID, Chat chat) throws Exception {
        return userRepository.findById(userID).map(user -> {
            chat.setUser(user);
            Date date = new Date();
            chat.setQuestionDate(date);
            chat.setAnswerDate(date);
            return chatRepository.save(chat);
        }).orElseThrow(() -> new Exception("Not Found"));

    }

    // Function for creating an object of chat as well as saving date and time

    public void saveChat(Chat chat) {
        Date date = new Date();
        chat.setQuestionDate(date);
        chat.setAnswerDate(date);
        chatRepository.save(chat);

    }

    // Function for searching a chat by id

    public Chat getChat(long id) {
        return chatRepository.findById(id).get();
    }

    // Function for deleting a chat object

    public void deleteChat(long id) {
        chatRepository.deleteById(id);
    }

    // Searching a chat by requesting parameter id of the particular question

    public ResponseEntity<Chat> GetQuestionById(@RequestParam("question") Long id) {
        Optional<Chat> chat = chatRepository.findById(id);
        if (chat.isPresent()) {
            return ResponseEntity.ok().body(chat.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }



}