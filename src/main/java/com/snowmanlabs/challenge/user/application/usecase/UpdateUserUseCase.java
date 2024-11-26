package com.snowmanlabs.challenge.user.application.usecase;

import com.snowmanlabs.challenge.user.application.dto.UpdateUserDTO;
import com.snowmanlabs.challenge.user.application.dto.UserDTO;
import com.snowmanlabs.challenge.user.domain.exception.UserNotFoundException;
import com.snowmanlabs.challenge.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final UserRepository userRepository;

    public UserDTO execute(final Long id, final UpdateUserDTO dto) {
        var domainUser = userRepository.findById(id);

        if (Objects.isNull(domainUser))
            throw new UserNotFoundException(String.format("The user with id %s was not found.", id));

        var updateUser = dto.toDomain(domainUser);
        return UserDTO.fromDomain(userRepository.update(id, updateUser));
    }

}
