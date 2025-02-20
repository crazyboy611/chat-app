package com.dtrung.chatapp.service.impl;

import com.dtrung.chatapp.model.Conversation;
import com.dtrung.chatapp.model.Message;
import com.dtrung.chatapp.model.MessageDeliveryStatus;
import com.dtrung.chatapp.model.User;
import com.dtrung.chatapp.repository.ConversationRepository;
import com.dtrung.chatapp.repository.MessageRepository;
import com.dtrung.chatapp.repository.UserRepository;
import com.dtrung.chatapp.service.ChatService;
import com.dtrung.chatapp.service.OnlineOfflineService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final SimpMessageSendingOperations messagingTemplate;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final OnlineOfflineService onlineOfflineService;
    @Override
    public Message sendMessage(
            String conversationId,
            Message message,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        Conversation conversation = conversationRepository
                .findByConvId(conversationId);
        UUID receiverId = message.getToUser();
//        UUID senderId = message.getFromUser();
        User sender = userRepository.findByUsername(Objects.requireNonNull(headerAccessor.getUser()).getName());
        message.setFromUser(sender.getId());
        message.setSendTime(LocalDateTime.now());
        boolean isUserOnline = onlineOfflineService.isOnlineUser(receiverId);
        boolean isUserSubscribed = onlineOfflineService.isUserSubscribed(
                receiverId,
                "/topic/" + conversationId
        );
        if (!isUserOnline) {
            message.setDeliveryStatus(MessageDeliveryStatus.NOT_DELIVERED);
        } else if(!isUserSubscribed) {
            message.setDeliveryStatus(MessageDeliveryStatus.DELIVERED);
            messagingTemplate.convertAndSend("/topic/" + receiverId.toString(), message);
        }else{
            message.setDeliveryStatus(MessageDeliveryStatus.SEEN);
            messagingTemplate.convertAndSend("/topic/" + conversationId, message);
        }
        message.setConversation(conversation);
        conversation.getMessages().add(message);
        messageRepository.save(message);
        conversationRepository.save(conversation);
        return message;
    }

    @Override
    public List<Message> getMessages(String conversationId) {
        return messageRepository.findByConversationId(conversationId);
    }
}
