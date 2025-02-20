package com.dtrung.chatapp.service.impl;

import com.dtrung.chatapp.model.User;
import com.dtrung.chatapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userWithEmail = userRepository.findByEmail(username);
        Optional<User> userWithPhoneNumber = userRepository.findByPhoneNumber(username);
        if (userWithEmail.isPresent()) {
            return userWithEmail.get();
        }
        if (userWithPhoneNumber.isPresent()) {
            return userWithPhoneNumber.get();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
