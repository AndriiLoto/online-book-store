package online.book.store.bookstore.validation.isbn;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class IsbnValidator implements ConstraintValidator<Isbn, String> {
    private static final String PATTERN_ISBN =
            "^(?:ISBN(?:-10)?:?\\s*)?"
                    + "([0-9]{9}[0-9X]|[0-9]{13}|(?:[0-9]-?){9}[0-9X]|(?:[0-9]-?){12}[0-9])$";

    private static final Pattern PATTERN = Pattern.compile(PATTERN_ISBN);

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext context) {
        return isbn != null && PATTERN.matcher(isbn).matches();
    }
}
