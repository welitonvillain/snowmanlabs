package com.snowmanlabs.challenge.user.infrastructure.out.persistence.entity;

import com.snowmanlabs.challenge.user.domain.entity.User;
import com.snowmanlabs.challenge.user.domain.type.Level;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    private Level level;


    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
                .id(user.id())
                .username(user.username())
                .password(user.password())
                .email(user.email())
                .level(user.level())
                .build();
    }

    public User toDomain() {
        return new User(this.id, this.username, this.password, this.email, this.level);
    }

}
