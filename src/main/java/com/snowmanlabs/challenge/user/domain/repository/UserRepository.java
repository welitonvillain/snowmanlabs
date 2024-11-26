package com.snowmanlabs.challenge.user.domain.repository;

import com.snowmanlabs.challenge.user.domain.entity.User;

public interface UserRepository {

    User save(User user);
    User findByUsername(String username);
    void delete(Long id);
    User update(Long id, User user);
    User findById(Long id);

}
