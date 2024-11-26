package com.snowmanlabs.challenge.user.application.dto;

import com.snowmanlabs.challenge.user.domain.entity.User;
import com.snowmanlabs.challenge.user.domain.type.Level;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(
        @NotEmpty String password,
        @NotEmpty String email,
        @NotNull Level level
) {

    public User toDomain(User user) {
        return new User(
            user.id(),
            user.username(),
            this.password,
            this.email,
            this.level
        );
    }

}
