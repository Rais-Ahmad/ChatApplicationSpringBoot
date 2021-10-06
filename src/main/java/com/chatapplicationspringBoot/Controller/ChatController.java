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

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@EnableSwagger2
@RestController
@RequestMapping("/chat")
public class ChatController {
    private static final Logger LOG = LogManager.getLogger(ChatController.class);
    private String key = "da6d27f1-a033-44a9-88aa-a8a5f64a85db";

    public boolean authorization(String checkKey) {
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

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Get All Chats
     * @param authValue
     * @return
     */

    @GetMapping("/allChats")
    public ResponseEntity<Object> chatList(@RequestHeader("Authorization") String authValue) {
        if (authorization(authValue)) {
            List<Chat> chatList = chatService.listAllUser();
            LOG.info("List of Chats : " + chatList);
            return new ResponseEntity(chatList, HttpStatus.OK);
        } else return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Get All Chats by Id
     * @param authValue
     * @param id
     * @return
     */
    @GetMapping("/getChat/{id}")
    public ResponseEntity<Object> getChatById(@RequestHeader("Authorization") String authValue, @PathVariable Long id) {
        if (authorization(authValue)) {
            try {
                Chat chat = chatService.getChat(id);
                LOG.info("user id is: " + id);
                return new ResponseEntity<>(chat, HttpStatus.CREATED);
            } catch (NoSuchElementException e) {
                LOGGER.error(e.getMessage(), e);
                return new ResponseEntity<>("Chat not found incorrect id ", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Not authorize", HttpStatus.UNAUTHORIZED);

        }
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Get Chat of a particular question by its Id
     * @param checkKey
     * @param id
     * @return
     */
    @GetMapping("/question")
    public ResponseEntity<Chat> getQuestionById(@RequestHeader("Authorization") String checkKey, @RequestParam("question") Long id) {
        try {
            if (authorization(checkKey) == true) {
                LOG.info("Get User by Id:  " + id);
                return chatService.GetQuestionById(id);
            } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException e) {
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Update Chat
     * @param authValue
     * @param chat
     * @return
     */
    @PutMapping("/updateChat")
    public ResponseEntity<Object> updateChat(@RequestHeader("Authorization") String authValue, @RequestBody Chat chat) {
        if (authorization(authValue)) {
            try {
                chatService.saveChat(chat);
                LOG.info("User updated: " + chat);
                return new ResponseEntity<>("updated successfully", HttpStatus.OK);
            } catch (NoSuchElementException e) {
                LOGGER.error(e.getMessage(), e);
                return new ResponseEntity<>("Chat not found ", HttpStatus.NOT_FOUND);
            }
        } else return new ResponseEntity<>(" not authorize ", HttpStatus.UNAUTHORIZED);
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Delete Chat
     * @param checkKey
     * @param id
     * @return
     */
    @DeleteMapping("/deleteChat/{id}")
    public ResponseEntity<String> deleteChat(@RequestHeader("Authorization") String checkKey, @PathVariable long id) {

        try {
            chatService.deleteChat(id);
            LOG.info(" User at id: " + id + "  is deleted! ");
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            LOGGER.error(e.getMessage(), e);
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

}
