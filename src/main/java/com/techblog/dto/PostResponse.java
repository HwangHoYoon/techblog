package com.techblog.dto;

import com.techblog.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String contentMarkdown;
    private String contentHtml;
    private String slug;
    private PostStatus status;
    private String username;
    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;
}
