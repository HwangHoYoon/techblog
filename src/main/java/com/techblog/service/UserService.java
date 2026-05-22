package com.techblog.service;

import com.techblog.domain.User;
import com.techblog.dto.UserResponse;
import com.techblog.exception.BusinessException;
import com.techblog.exception.ErrorCode;
import com.techblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        return UserResponse.builder()
                .username(user.getUsername())
                .bio(user.getBio())
                .profileImage(user.getProfileImage())
                .createdAt(user.getCreatedAt())
                .build();
    }

    @Transactional
    public void updateProfile(Long userId, String bio, String profileImage) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        user.updateProfile(bio, profileImage);
    }
}
