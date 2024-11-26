package com.snowmanlabs.challenge.user.application.dto;

import com.snowmanlabs.challenge.user.domain.entity.User;
import com.snowmanlabs.challenge.user.domain.type.Level;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(
        @NotEmpty String username,
        @NotEmpty String password,
        @NotEmpty String email,
        @NotNull Level level
) {

    public User toDomain() {
        return new User(
                null,
                this.username,
                this.password,
                this.email,
                this.level
        );
    }

}
