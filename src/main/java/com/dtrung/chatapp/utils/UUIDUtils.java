package com.dtrung.chatapp.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDUtils {
    public String getConversationId(UUID user1, UUID user2) {
        return
                user1.compareTo(user2) > 0 ?
                        user2 + "_" + user1 :
                        user1 + "_" + user2;
    }
}
