package online.book.store.bookstore.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import online.book.store.bookstore.validation.isbn.Isbn;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@ToString
public class CreateBookRequestDto {
    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be less than 255 characters")
    private String title;
    @NotBlank(message = "Author is required")
    @Size(max = 255, message = "Author must be less than 255 characters")
    private String author;
    @NotBlank(message = "Isbn is required")
    @Isbn
    private String isbn;
    @Min(value = 0,message = "Price should not be negative")
    @NotNull(message = "Price is required")
    private BigDecimal price;
    @Size(max = 1000, message = "Description must be less than 1000 characters")
    private String description;
    @URL(message = "Should be valid url")
    private String coverImage;
}
