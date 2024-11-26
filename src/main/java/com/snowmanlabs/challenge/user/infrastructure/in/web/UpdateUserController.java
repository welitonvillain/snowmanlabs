package com.snowmanlabs.challenge.user.infrastructure.in.web;

import com.snowmanlabs.challenge.user.application.dto.UpdateUserDTO;
import com.snowmanlabs.challenge.user.application.dto.UserDTO;
import com.snowmanlabs.challenge.user.application.usecase.UpdateUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Operations related to the users model.")
public class UpdateUserController {

    private final UpdateUserUseCase updateUserUseCase;

    @PutMapping("/{id}")
    @Operation(summary = "Update a User")
    public UserDTO execute(@PathVariable("id") Long id, @Valid  @RequestBody UpdateUserDTO dto) {
        return updateUserUseCase.execute(id, dto);
    }


}
