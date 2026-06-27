package online.book.store.bookstore.repository.book;

import lombok.RequiredArgsConstructor;
import online.book.store.bookstore.dto.book.BookSearchParameters;
import online.book.store.bookstore.model.Book;
import online.book.store.bookstore.repository.SpecificationBuilder;
import online.book.store.bookstore.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParameters searchParameters) {
        Specification<Book> spec = (root, query, cb) -> cb.conjunction();
        if (searchParameters.author() != null && searchParameters.author().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(AUTHOR)
                    .getSpecification(searchParameters.author()));
        }
        if (searchParameters.title() != null && searchParameters.title().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(TITLE)
                    .getSpecification(searchParameters.title()));
        }
        return spec;
    }
}
