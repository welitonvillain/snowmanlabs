package com.snowmanlabs.challenge.user.application.usecase;

import com.snowmanlabs.challenge.user.application.dto.UserDTO;
import com.snowmanlabs.challenge.user.domain.entity.User;
import com.snowmanlabs.challenge.user.domain.exception.UserNotFoundException;
import com.snowmanlabs.challenge.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FindUserByUsernameUseCase {

    private final UserRepository userRepository;

    public UserDTO execute(final String username) {
        User domainUser = userRepository.findByUsername(username);

        if (Objects.isNull(domainUser))
            throw new UserNotFoundException(String.format("The user with username %s was not found.", username));

        return UserDTO.fromDomain(domainUser);
    }

}
