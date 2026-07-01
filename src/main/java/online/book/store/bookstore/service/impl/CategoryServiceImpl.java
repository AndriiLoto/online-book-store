package online.book.store.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import online.book.store.bookstore.dto.category.CategoryDto;
import online.book.store.bookstore.dto.category.CreateCategoryRequestDto;
import online.book.store.bookstore.exception.EntityNotFoundException;
import online.book.store.bookstore.mapper.CategoryMapper;
import online.book.store.bookstore.model.Category;
import online.book.store.bookstore.repository.category.CategoryRepository;
import online.book.store.bookstore.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Page<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository
                .findAll(pageable)
                .map(categoryMapper::toCategoryDto);
    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = getCategoryById(id);
        return categoryMapper.toCategoryDto(category);
    }

    @Override
    public CategoryDto save(CreateCategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toCategory(categoryRequestDto);
        return categoryMapper.toCategoryDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto update(Long id, CreateCategoryRequestDto categoryRequestDto) {
        Category category = getCategoryById(id);
        categoryMapper.updateCategoryFromDto(categoryRequestDto, category);
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toCategoryDto(updatedCategory);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    private Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category not found with id: " + id)
        );
    }
}
