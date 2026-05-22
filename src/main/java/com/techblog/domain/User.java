package com.techblog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String bio;

    private String profileImage;

    @Builder
    public User(String email, String username, String password, String bio, String profileImage) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.profileImage = profileImage;
    }

    public void updateProfile(String bio, String profileImage) {
        this.bio = bio;
        this.profileImage = profileImage;
    }
}
