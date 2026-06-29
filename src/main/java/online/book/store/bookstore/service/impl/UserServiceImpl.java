package online.book.store.bookstore.service.impl;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import online.book.store.bookstore.dto.user.UserRegistrationRequestDto;
import online.book.store.bookstore.dto.user.UserResponseDto;
import online.book.store.bookstore.exception.RegistrationException;
import online.book.store.bookstore.mapper.UserMapper;
import online.book.store.bookstore.model.Role;
import online.book.store.bookstore.model.RoleName;
import online.book.store.bookstore.model.User;
import online.book.store.bookstore.repository.role.RoleRepository;
import online.book.store.bookstore.repository.user.UserRepository;
import online.book.store.bookstore.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.existsByEmail((requestDto.getEmail()))) {
            throw new RegistrationException("Email already in use: " + requestDto.getEmail());
        }
        User user = userMapper.toUser(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.USER)
                .orElseThrow(() ->
                        new RegistrationException("Default role USER not found in database"));
        user.setRoles(Set.of(userRole));
        return userMapper.toUserResponseDto(userRepository.save(user));
    }
}
