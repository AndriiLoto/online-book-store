package online.book.store.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import online.book.store.bookstore.dto.user.UserRegistrationRequestDto;
import online.book.store.bookstore.dto.user.UserResponseDto;
import online.book.store.bookstore.exception.RegistrationException;
import online.book.store.bookstore.mapper.UserMapper;
import online.book.store.bookstore.model.User;
import online.book.store.bookstore.repository.user.UserRepository;
import online.book.store.bookstore.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.existsByEmail((requestDto.getEmail()))) {
            throw new RegistrationException("Email already in use!");
        }
        User user = userMapper.toUser(requestDto);
        return userMapper.toUserResponseDto(userRepository.save(user));
    }
}
