package online.book.store.bookstore.mapper;

import online.book.store.bookstore.config.MapperConfig;
import online.book.store.bookstore.dto.category.CategoryDto;
import online.book.store.bookstore.dto.category.CreateCategoryRequestDto;
import online.book.store.bookstore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(config = MapperConfig.class,
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface CategoryMapper {
    CategoryDto toCategoryDto(Category category);

    Category toCategory(CreateCategoryRequestDto categoryRequestDto);

    void updateCategoryFromDto(CreateCategoryRequestDto categoryRequestDto,
                               @MappingTarget Category category);
}
