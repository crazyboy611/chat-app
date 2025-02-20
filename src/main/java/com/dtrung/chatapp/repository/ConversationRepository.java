package com.dtrung.chatapp.repository;

import com.dtrung.chatapp.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, String> {
    Conversation findByConvId(String conversationId);

}
