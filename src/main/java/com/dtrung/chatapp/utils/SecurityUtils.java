package com.dtrung.chatapp.utils;

import com.dtrung.chatapp.model.User;
import com.dtrung.chatapp.repository.UserRepository;
import com.dtrung.chatapp.service.impl.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtils {
    private final UserDetailServiceImpl userDetailService;
    public User getCurrentUser() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User) userDetailService.loadUserByUsername(jwt.getSubject());
    }
}
