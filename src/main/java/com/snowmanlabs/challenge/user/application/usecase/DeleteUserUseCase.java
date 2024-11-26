package com.snowmanlabs.challenge.user.application.usecase;

import com.snowmanlabs.challenge.user.domain.exception.UserNotFoundException;
import com.snowmanlabs.challenge.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCase {

    private final UserRepository userRepository;

    public void execute(final Long id) {
        var user = userRepository.findById(id);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(String.format("The user with id %s was not found.", id));
        }

        userRepository.delete(id);
    }

}
