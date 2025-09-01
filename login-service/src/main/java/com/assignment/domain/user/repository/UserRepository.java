package com.assignment.domain.user.repository;

import com.assignment.domain.user.model.User;
import com.assignment.domain.user.model.UserEmail;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(UserEmail email);

}
