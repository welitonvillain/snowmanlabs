package com.snowmanlabs.challenge.user.application.usecase;

import com.snowmanlabs.challenge.user.application.dto.CreateUserDTO;
import com.snowmanlabs.challenge.user.application.dto.UserDTO;
import com.snowmanlabs.challenge.user.domain.exception.UserDomainException;
import com.snowmanlabs.challenge.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;

    public UserDTO execute(final CreateUserDTO dto) {
        if (Objects.nonNull(userRepository.findByUsername(dto.username()))) {
            throw new UserDomainException("There is already a registered user with the same username.");
        }

        var user = dto.toDomain();
        return UserDTO.fromDomain(userRepository.save(user));
    }

}
