package com.assignment.domain.user.service;

import static com.assignment.domain.user.message.ExceptionMessage.SIGNUP_DUPLICATED_EMAIL;
import static com.assignment.domain.user.message.ExceptionMessage.SIGNUP_DUPLICATED_USERNAME;

import com.assignment.domain.user.dto.request.UserSignUpDto;
import com.assignment.domain.user.dto.response.SignUpUserResponseDto;
import com.assignment.domain.user.exception.UserException;
import com.assignment.domain.user.model.User;
import com.assignment.domain.user.model.UserEmail;
import com.assignment.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public SignUpUserResponseDto signUpUser(UserSignUpDto userSignUpDto) {

        // 회원 정보 중복 확인
        checkDuplicatedUsername(userSignUpDto.username());
        checkDuplicatedEmail(new UserEmail(userSignUpDto.email()));
        User user = User.of(userSignUpDto);
        userRepository.save(user);

        return SignUpUserResponseDto.from(user);
    }


    @Override
    public void checkDuplicatedEmail(UserEmail userEmail) {

        if (userRepository.existsByEmail(userEmail)) {
            throw new UserException(SIGNUP_DUPLICATED_EMAIL);
        }
    }

    @Override
    public void checkDuplicatedUsername(String username) {

        if (userRepository.existsByUsername(username)) {
            throw new UserException(SIGNUP_DUPLICATED_USERNAME);
        }
    }


}
