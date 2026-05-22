package com.techblog.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserResponse {
    private String username;
    private String bio;
    private String profileImage;
    private long postCount;
    private LocalDateTime createdAt;
}
