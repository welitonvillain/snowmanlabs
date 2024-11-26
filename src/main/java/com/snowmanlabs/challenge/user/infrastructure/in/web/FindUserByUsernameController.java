package com.snowmanlabs.challenge.user.infrastructure.in.web;

import com.snowmanlabs.challenge.user.application.dto.UserDTO;
import com.snowmanlabs.challenge.user.application.usecase.FindUserByUsernameUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Operations related to the users model.")
public class FindUserByUsernameController {

    private final FindUserByUsernameUseCase findUserByUsernameUseCase;

    @GetMapping
    @Operation(summary = "Find a User by username")
    public UserDTO execute(@RequestParam("username") String username) {
        return findUserByUsernameUseCase.execute(username);
    }


}
