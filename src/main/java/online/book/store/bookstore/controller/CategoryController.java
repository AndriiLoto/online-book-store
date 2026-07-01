package online.book.store.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import online.book.store.bookstore.dto.book.BookDto;
import online.book.store.bookstore.dto.category.CategoryDto;
import online.book.store.bookstore.dto.category.CreateCategoryRequestDto;
import online.book.store.bookstore.service.BookService;
import online.book.store.bookstore.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Categories", description = "Endpoints for categories")
@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new category",
            description = "Creates a new category and returns the created category details")
    CategoryDto createCategory(@RequestBody CreateCategoryRequestDto categoryRequestDto) {
        return categoryService.save(categoryRequestDto);
    }

    @GetMapping
    @Operation(summary = "Get all categories",
            description = "Returns list of categories with pagination")
    Page<CategoryDto> getAllCategories(@Parameter(description = "Pagination parameters")
                                       Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by id",
            description = "Returns one category by id")
    CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update category fields by id",
            description = "Updates one or more fields of category by id")
    CategoryDto updateCategoryById(@PathVariable Long id,
                                    @RequestBody CreateCategoryRequestDto categoryRequestDto) {
        return categoryService.update(id, categoryRequestDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete category by id",
            description = "Deletes one category by id")
    void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @GetMapping("/{id}/books")
    Page<BookDto> getBooksByCategoryId(@PathVariable Long id, Pageable pageable) {
        return bookService.findByCategory(id, pageable);
    }
}
