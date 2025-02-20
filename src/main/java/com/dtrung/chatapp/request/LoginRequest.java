package com.dtrung.chatapp.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    @NonNull
            @NotBlank(message = "Username cannot be blank")
    String username;

    @NonNull
            @NotBlank(message = "Password cannot be blank")
    String password;
}
