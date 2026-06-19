package online.book.store.bookstore.repository;

import online.book.store.bookstore.dto.BookSearchParameters;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {

    Specification<T> build(BookSearchParameters searchParameters);

}
