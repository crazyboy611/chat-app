package com.dtrung.chatapp.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponse {
    UUID userId;
    String username;
    String token;
    String tokenType;
    String avatar;
    List<String> roles;
}
