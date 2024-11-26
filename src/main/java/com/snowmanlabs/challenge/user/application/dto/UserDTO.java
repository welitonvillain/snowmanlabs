package com.snowmanlabs.challenge.user.application.dto;

import com.snowmanlabs.challenge.user.domain.entity.User;
import com.snowmanlabs.challenge.user.domain.type.Level;

public record UserDTO(
        Long id,
        String username,
        String password,
        String email,
        Level level
) {

    public User toDomain() {
        return new User(
                this.id,
                this.username,
                this.password,
                this.email,
                this.level
        );
    }

    public static UserDTO fromDomain(final User user) {
        return new UserDTO(user.id(), user.username(), user.password(), user.email(), user.level());
    }

}
