package com.techblog.dto;

import com.techblog.domain.PostStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String contentMarkdown;

    private PostStatus status;
}
