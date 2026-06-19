package online.book.store.bookstore.repository.book.spec;

import java.util.Arrays;
import online.book.store.bookstore.model.Book;
import online.book.store.bookstore.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<Book> {
    @Override
    public String getKey() {
        return "title";
    }

    public Specification<Book> getSpecification(String[] parameters) {
        System.out.println(Arrays.toString(parameters));
        return (root, query, criteriaBuilder) ->
                root.get("title").in(Arrays.stream(parameters).toArray());
    }
}
