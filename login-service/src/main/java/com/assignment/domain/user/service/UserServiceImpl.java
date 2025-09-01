package com.assignment.domain.user.service;

import static com.assignment.domain.user.message.ExceptionMessage.LOGIN_NOT_FOUND_USER;
import static com.assignment.domain.user.message.ExceptionMessage.LOGIN_NOT_MATCH_PASSWORD;
import static com.assignment.domain.user.message.ExceptionMessage.SELECT_NOT_FOUND_USER;
import static com.assignment.domain.user.message.ExceptionMessage.SIGNUP_DUPLICATED_EMAIL;
import static com.assignment.domain.user.message.ExceptionMessage.SIGNUP_DUPLICATED_USERNAME;

import com.assignment.domain.user.dto.request.UserSignUpDto;
import com.assignment.domain.user.dto.response.GetUserResponseDto;
import com.assignment.domain.user.dto.response.LoginRequestDto;
import com.assignment.domain.user.dto.response.SignUpUserResponseDto;
import com.assignment.domain.user.exception.UserException;
import com.assignment.domain.user.model.User;
import com.assignment.domain.user.model.UserEmail;
import com.assignment.domain.user.repository.UserRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional // write
    public SignUpUserResponseDto signUpUser(UserSignUpDto dto) {
        checkDuplicatedUsername(dto.username());
        checkDuplicatedEmail(new UserEmail(dto.email()));
        User user = User.of(dto);
        userRepository.save(user);
        return SignUpUserResponseDto.from(user);
    }

    @Override
    public Long loginAndGetUserId(LoginRequestDto req) {
        User user = userRepository.findByUsername(req.username())
                .orElseThrow(() -> new UserException(LOGIN_NOT_FOUND_USER));
        if (!Objects.equals(user.getPassword(), req.password())) {
            throw new UserException(LOGIN_NOT_MATCH_PASSWORD);
        }
        return user.getId();
    }

    @Override
    public GetUserResponseDto getUser(Long userId, Long requesterId) {
        if (requesterId == null) {
            throw new UserException(SELECT_NOT_FOUND_USER);
        }
        if (!userId.equals(requesterId)) {
            throw new UserException(SELECT_NOT_FOUND_USER);
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(SELECT_NOT_FOUND_USER));
        return GetUserResponseDto.from(user);
    }

    private void checkDuplicatedUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UserException(SIGNUP_DUPLICATED_USERNAME);
        }
    }

    private void checkDuplicatedEmail(UserEmail userEmail) {
        if (userRepository.existsByEmail(userEmail)) {
            throw new UserException(SIGNUP_DUPLICATED_EMAIL);
        }
    }
}