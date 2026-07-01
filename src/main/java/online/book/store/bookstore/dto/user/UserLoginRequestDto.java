package online.book.store.bookstore.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginRequestDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
