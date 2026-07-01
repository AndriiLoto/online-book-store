package online.book.store.bookstore.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import online.book.store.bookstore.config.MapperConfig;
import online.book.store.bookstore.dto.book.BookDto;
import online.book.store.bookstore.dto.book.BookDtoWithoutCategoryIds;
import online.book.store.bookstore.dto.book.CreateBookRequestDto;
import online.book.store.bookstore.model.Book;
import online.book.store.bookstore.model.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    BookDto toBookDto(Book book);

    Book toModel(CreateBookRequestDto bookRequestDtoDto);

    void updateBookFromDto(CreateBookRequestDto bookRequestDto, @MappingTarget Book book);

    BookDtoWithoutCategoryIds toBookDtoWithoutCategoryIds(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        if (book.getCategories() != null) {
            Set<Long> categoryIds = book.getCategories()
                    .stream()
                    .map(Category::getId)
                    .collect(Collectors.toSet());
            bookDto.setCategoryIds(categoryIds);
        }
    }
}
