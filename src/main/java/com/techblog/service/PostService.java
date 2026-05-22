package com.techblog.service;

import com.techblog.domain.Post;
import com.techblog.domain.PostStatus;
import com.techblog.domain.User;
import com.techblog.dto.PostCreateRequest;
import com.techblog.dto.PostResponse;
import com.techblog.exception.BusinessException;
import com.techblog.exception.ErrorCode;
import com.techblog.repository.PostRepository;
import com.techblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final MarkdownProcessor markdownProcessor;

    @Transactional
    public PostResponse createPost(Long userId, PostCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        String html = markdownProcessor.markdownToHtml(request.getContentMarkdown());
        String slug = markdownProcessor.createSlug(request.getTitle());

        Post post = Post.builder()
                .user(user)
                .title(request.getTitle())
                .contentMarkdown(request.getContentMarkdown())
                .contentHtml(html)
                .slug(slug)
                .status(request.getStatus() != null ? request.getStatus() : PostStatus.DRAFT)
                .build();

        Post savedPost = postRepository.save(post);
        return toResponse(savedPost);
    }

    @Transactional
    public PostResponse updatePost(Long userId, Long postId, PostCreateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        if (!post.getUser().getId().equals(userId)) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        String html = markdownProcessor.markdownToHtml(request.getContentMarkdown());
        String slug = markdownProcessor.createSlug(request.getTitle());

        post.update(request.getTitle(), request.getContentMarkdown(), html, slug, request.getStatus());
        return toResponse(post);
    }

    @Transactional
    public void deletePost(Long userId, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        if (!post.getUser().getId().equals(userId)) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        postRepository.delete(post);
    }

    public PostResponse getPost(String username, String slug) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        Post post = postRepository.findByUserAndSlug(user, slug)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        if (post.getStatus() == PostStatus.DRAFT) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        return toResponse(post);
    }

    public Page<PostResponse> getPosts(Pageable pageable) {
        return postRepository.findByStatusOrderByPublishedAtDesc(PostStatus.PUBLISHED, pageable)
                .map(this::toResponse);
    }

    public Page<PostResponse> getUserPosts(String username, Pageable pageable) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        return postRepository.findByUserAndStatus(user, PostStatus.PUBLISHED, pageable)
                .map(this::toResponse);
    }

    private PostResponse toResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .contentMarkdown(post.getContentMarkdown())
                .contentHtml(post.getContentHtml())
                .slug(post.getSlug())
                .status(post.getStatus())
                .username(post.getUser().getUsername())
                .publishedAt(post.getPublishedAt())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
