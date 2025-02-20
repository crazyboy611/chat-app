package com.dtrung.chatapp.service;

import com.dtrung.chatapp.model.Message;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import java.util.List;

public interface ChatService {
    Message sendMessage(String conversationId,
                        Message message,
                        SimpMessageHeaderAccessor headerAccessor
    );
    List<Message> getMessages(String conversationId);
}
