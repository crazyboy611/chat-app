package com.dtrung.chatapp.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OnlineConversation { // tạo conversation id giữa online user và online user có trong set
    String convId;
    UUID otherSideUserId;
    String otherSideUserName;
    String otherSideUserAvatar;
    boolean isOnline;
}
