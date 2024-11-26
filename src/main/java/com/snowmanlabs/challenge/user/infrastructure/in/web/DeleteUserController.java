package com.snowmanlabs.challenge.user.infrastructure.in.web;

import com.snowmanlabs.challenge.user.application.usecase.DeleteUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Operations related to the users model.")
public class DeleteUserController {

    private final DeleteUserUseCase deleteUserUseCase;

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a User by ID")
    public void execute(@PathVariable("id") Long id) {
        deleteUserUseCase.execute(id);
    }


}
