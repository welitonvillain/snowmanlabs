package com.snowmanlabs.challenge.user.infrastructure.in.web;

import com.snowmanlabs.challenge.user.application.dto.CreateUserDTO;
import com.snowmanlabs.challenge.user.application.dto.UserDTO;
import com.snowmanlabs.challenge.user.application.usecase.CreateUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Operations related to the users model.")
public class CreateUserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    @Operation(summary = "Create a User")
    public UserDTO execute(@Valid @RequestBody CreateUserDTO dto) {
        return createUserUseCase.execute(dto);
    }


}
