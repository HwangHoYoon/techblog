package com.techblog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "posts")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contentMarkdown;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contentHtml;

    @Column(unique = true, nullable = false)
    private String slug;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostStatus status;

    private LocalDateTime publishedAt;

    @Builder
    public Post(User user, String title, String contentMarkdown, String contentHtml, String slug, PostStatus status) {
        this.user = user;
        this.title = title;
        this.contentMarkdown = contentMarkdown;
        this.contentHtml = contentHtml;
        this.slug = slug;
        this.status = status;
        if (status == PostStatus.PUBLISHED) {
            this.publishedAt = LocalDateTime.now();
        }
    }

    public void update(String title, String contentMarkdown, String contentHtml, String slug, PostStatus status) {
        this.title = title;
        this.contentMarkdown = contentMarkdown;
        this.contentHtml = contentHtml;
        this.slug = slug;
        if (this.status != PostStatus.PUBLISHED && status == PostStatus.PUBLISHED) {
            this.publishedAt = LocalDateTime.now();
        }
        this.status = status;
    }
}
