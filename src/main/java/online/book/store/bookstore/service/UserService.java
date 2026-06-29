package online.book.store.bookstore.service;

import online.book.store.bookstore.dto.user.UserRegistrationRequestDto;
import online.book.store.bookstore.dto.user.UserResponseDto;
import online.book.store.bookstore.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
