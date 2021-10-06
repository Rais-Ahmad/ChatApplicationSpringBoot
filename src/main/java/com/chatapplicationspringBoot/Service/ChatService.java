package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Chat;
import com.chatapplicationspringBoot.Repository.ChatRepository;
import com.chatapplicationspringBoot.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChatService {
    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Get all Chats
     * @return
     */
    public List<Chat> listAllUser() {
        return chatRepository.findAll();
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Function for creating an object of chat as well as saving date and time
     * @param chat
     */
    public void saveChat(Chat chat) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        chat.setQuestionDate(dtf.format(now));
        chat.setAnswerDate(dtf.format(now));
        chatRepository.save(chat);

    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Function for searching a chat by id
     * @param id
     * @return
     */

    public Chat getChat(long id) {
        return chatRepository.findById(id).get();
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Function for deleting a chat object
     * @param id
     */

    public void deleteChat(long id) {
        chatRepository.deleteById(id);
    }

    /**
     * @Author Rais Ahmad
     * @Date 09-06-2021
     * @Discription Searching a chat by requesting parameter id of the particular question
     * @param id
     * @return
     */

    public ResponseEntity<Chat> GetQuestionById(@RequestParam("question") Long id) {
        Optional<Chat> chat = chatRepository.findById(id);
        if (chat.isPresent()) {
            return ResponseEntity.ok().body(chat.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }


}