package online.book.store.bookstore.security;

import lombok.RequiredArgsConstructor;
import online.book.store.bookstore.dto.user.UserLoginRequestDto;
import online.book.store.bookstore.dto.user.UserLoginResponseDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserLoginResponseDto authenticate(UserLoginRequestDto requestDto) {
        String token = jwtUtil.generateToken(requestDto.getEmail());
        UserLoginResponseDto responseDto = new UserLoginResponseDto();
        responseDto.setToken(token);
        return responseDto;
    }
}
