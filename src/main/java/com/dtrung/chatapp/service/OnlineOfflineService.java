package com.dtrung.chatapp.service;

import com.dtrung.chatapp.model.Conversation;
import com.dtrung.chatapp.model.Message;
import com.dtrung.chatapp.model.MessageDeliveryStatus;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface OnlineOfflineService {
    void addOnlineUser(Principal user);
    void removeOnlineUser(Principal user);
    boolean isOnlineUser(UUID userId);
    boolean isUserSubscribed(UUID userId, String subscription);
    void addUserSubscribed(Principal user, String subscribedChannel);
    void removeUserSubscribed(Principal user, String subscribedChannel);
    Map<String, Set<String>> getUserSubscribed();
    Set<UUID> getOnlineUsers();
    void notifySender(
            UUID senderId,
            String conversationId,
            List<Conversation> conversations,
            MessageDeliveryStatus messageStatus
            );
}
