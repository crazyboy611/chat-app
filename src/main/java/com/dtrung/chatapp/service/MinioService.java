package com.dtrung.chatapp.service;

import org.springframework.web.multipart.MultipartFile;

public interface MinioService {
    String uploadAvatar(MultipartFile file);
}
