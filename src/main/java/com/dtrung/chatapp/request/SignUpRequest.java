package com.dtrung.chatapp.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequest {

    @NotBlank(message = "Username cannot be blank!")
    String username;

    @Email()
            @JsonInclude(JsonInclude.Include.NON_NULL)
    String email;

    String phoneNumber;

    @NotBlank(message = "Password cannot be blank")
    String password;

    MultipartFile avatar;
}
