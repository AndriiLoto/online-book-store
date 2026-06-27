package online.book.store.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import online.book.store.bookstore.dto.user.UserRegistrationRequestDto;
import online.book.store.bookstore.dto.user.UserResponseDto;
import online.book.store.bookstore.exception.RegistrationException;
import online.book.store.bookstore.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Endpoints for auth")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register new user",
            description = "Registers new user to the DB")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.register(requestDto);
    }
}
