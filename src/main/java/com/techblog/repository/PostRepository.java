package com.techblog.repository;

import com.techblog.domain.Post;
import com.techblog.domain.PostStatus;
import com.techblog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUserAndSlug(User user, String slug);
    Page<Post> findByUserAndStatus(User user, PostStatus status, Pageable pageable);
    Page<Post> findByStatusOrderByPublishedAtDesc(PostStatus status, Pageable pageable);
    Page<Post> findByUser(User user, Pageable pageable);
}
