package com.chatapplicationspringBoot.Controller;

import com.chatapplicationspringBoot.Model.Chat;
import com.chatapplicationspringBoot.Service.ChatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.NoSuchElementException;


@EnableSwagger2
@RestController
@RequestMapping("/chat")
public class ChatController {
    private static final Logger LOG = LogManager.getLogger(ChatController.class);
    private String key = "da6d27f1-a033-44a9-88aa-a8a5f64a85db";

    public boolean Authorization(String checkKey) {
        if (checkKey.equals(key)) {
            return true;
        } else return false;
    }


    final
    ChatService chatService;

    /**
     * @param chatService
     * @Auther Rais Ahmad
     * @date 29/09/2021
     */
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chats")
    // public List<Chat> list() {
    public ResponseEntity<Object> list(@RequestHeader("Authorization") String authValue) {
        if (Authorization(authValue)) {
            List<Chat> chatList = chatService.listAllUser();
            return new ResponseEntity(chatList, HttpStatus.OK);
        } else return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> get(@RequestHeader("Authorization") String authValue, @PathVariable Long id) {
        if (Authorization(authValue)) {
            try {
                Chat chat = chatService.getChat(id);
                LOG.info("user id is: " + id);
                return new ResponseEntity<>(chat, HttpStatus.CREATED);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>("Chat not found incorrect id ", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);

        }
    }


    @GetMapping("/question")
    public ResponseEntity<Chat> getQuestionById(@RequestHeader("Authorization") String checkKey, @RequestParam("question") Long id) {

        if (Authorization(checkKey) == true) {
            return chatService.GetQuestionById(id);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestHeader("Authorization") String authValue, @RequestBody Chat chat) {
        if (Authorization(authValue)) {
            try {
                chatService.saveChat(chat);
                LOG.info("User updated: " + chat);
                return new ResponseEntity<>("updated successfully", HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>("Chat not found ", HttpStatus.NOT_FOUND);
            }
        } else return new ResponseEntity<>(" not authorize ", HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@RequestHeader("Authorization") String checkKey, @PathVariable long id) {

        try {
            chatService.deleteChat(id);

            LOG.info(" User at id: " + id + "  is deleted! ");
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

}
