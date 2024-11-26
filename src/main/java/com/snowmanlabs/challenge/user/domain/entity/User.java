package com.snowmanlabs.challenge.user.domain.entity;

import com.snowmanlabs.challenge.user.domain.type.Level;

public record User(Long id, String username, String password, String email, Level level) {
}
