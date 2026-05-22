package com.techblog.controller;

import com.techblog.dto.PostCreateRequest;
import com.techblog.dto.PostResponse;
import com.techblog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @AuthenticationPrincipal Long userId,
            @Valid @RequestBody PostCreateRequest request) {
        return ResponseEntity.ok(postService.createPost(userId, request));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @AuthenticationPrincipal Long userId,
            @PathVariable Long postId,
            @Valid @RequestBody PostCreateRequest request) {
        return ResponseEntity.ok(postService.updatePost(userId, postId, request));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @AuthenticationPrincipal Long userId,
            @PathVariable Long postId) {
        postService.deletePost(userId, postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<PostResponse>> getPosts(Pageable pageable) {
        return ResponseEntity.ok(postService.getPosts(pageable));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<Page<PostResponse>> getUserPosts(
            @PathVariable String username,
            Pageable pageable) {
        return ResponseEntity.ok(postService.getUserPosts(username, pageable));
    }

    @GetMapping("/{username}/{slug}")
    public ResponseEntity<PostResponse> getPost(
            @PathVariable String username,
            @PathVariable String slug) {
        return ResponseEntity.ok(postService.getPost(username, slug));
    }
}
