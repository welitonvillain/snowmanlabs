package com.snowmanlabs.challenge.user.infrastructure.out.persistence.repository;

import com.snowmanlabs.challenge.user.domain.entity.User;
import com.snowmanlabs.challenge.user.domain.exception.UserNotFoundException;
import com.snowmanlabs.challenge.user.domain.repository.UserRepository;
import com.snowmanlabs.challenge.user.infrastructure.out.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(final User user) {
        var entity = UserEntity.toEntity(user);
        return jpaUserRepository.save(entity).toDomain();
    }

    @Override
    public User findByUsername(final String username) {
        var entity = jpaUserRepository.findByUsername(username);
        return entity.map(UserEntity::toDomain).orElse(null);
    }

    @Override
    public void delete(final Long id) {
        jpaUserRepository.deleteById(id);
    }

    @Override
    public User update(final Long id, final User user) {
        return jpaUserRepository.findById(id)
                .map(entity -> {
                    entity.setEmail(user.email());
                    entity.setLevel(user.level());
                    entity.setUsername(user.username());
                    entity.setPassword(user.password());
                    return jpaUserRepository.save(entity).toDomain();
                })
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("The user with id %s was not found.", user.id()))
                );
    }

    @Override
    public User findById(final Long id) {
        var entity = jpaUserRepository.findById(id);
        return entity.map(UserEntity::toDomain).orElse(null);
    }
}
