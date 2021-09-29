package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Chat;
import com.chatapplicationspringBoot.Repository.ChatRepository;
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

    public List<Chat> listAllUser() {
        return chatRepository.findAll();
    }


    public void saveChat(Chat chat) {
        Date date = new Date();
        chat.setQuestionDate(date);
        chat.setAnswerDate(date);
        chatRepository.save(chat);

    }

//    public void deleteChat(long id) {
//        chatRepository.deleteById(id);
//    }

    public Chat getChat(long id) {
        return chatRepository.findById(id).get();
    }

    public void deleteChat(long id) {
        chatRepository.deleteById(id);
    }

    public ResponseEntity<Chat> GetQuestionById(@RequestParam("question") Long id) {
        Optional<Chat> chat = chatRepository.findById(id);
        if (chat.isPresent()) {
            return ResponseEntity.ok().body(chat.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}