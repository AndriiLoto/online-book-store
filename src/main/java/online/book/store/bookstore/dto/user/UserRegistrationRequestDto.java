package online.book.store.bookstore.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import online.book.store.bookstore.validation.fieldmatch.FieldMatch;

@Getter
@Setter
@ToString
@FieldMatch(field = "password", repeatedField = "repeatPassword")
public class UserRegistrationRequestDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 8, max = 25)
    private String password;
    @NotBlank
    private String repeatPassword;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String shippingAddress;
}
