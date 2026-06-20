package online.book.store.bookstore.repository.book.spec;

import java.util.Arrays;
import online.book.store.bookstore.model.Book;
import online.book.store.bookstore.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {

    private static final String AUTHOR = "author";

    @Override
    public String getKey() {
        return AUTHOR;

    }

    public Specification<Book> getSpecification(String[] parameters) {
        System.out.println(Arrays.toString(parameters));
        return (root, query, criteriaBuilder) ->
                root.get(AUTHOR).in(Arrays.stream(parameters).toArray());
    }
}
