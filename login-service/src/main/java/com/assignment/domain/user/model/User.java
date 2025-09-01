package com.assignment.domain.user.model;

import com.assignment.domain.user.dto.request.UserSignUpDto;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "p_user")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Embedded
    private UserEmail email;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


    @Builder(access = AccessLevel.PRIVATE)
    public User(String username, String password, UserEmail email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public static User of(UserSignUpDto userSignUpDto) {

        return User.builder()
                .username(userSignUpDto.username())
                .password(userSignUpDto.password())
                .email(new UserEmail(userSignUpDto.email()))
                .build();
    }

    public void deleteUser(Long deletedBy) {
        this.deletedAt = LocalDateTime.now();
    }
}

