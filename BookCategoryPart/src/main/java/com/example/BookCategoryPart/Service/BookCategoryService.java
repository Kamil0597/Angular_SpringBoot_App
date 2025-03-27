package com.example.BookCategoryPart.Service;

import com.example.BookCategoryPart.DTO.BookCategoryCollectionDTO;
import com.example.BookCategoryPart.DTO.BookCategoryCreateUpdateDTO;
import com.example.BookCategoryPart.DTO.BookCategoryReadDTO;
import com.example.BookCategoryPart.Entity.BookCategory;
import com.example.BookCategoryPart.Repository.BookCategoryRepository;
import com.example.BookCategoryPart.rest.BookCategoryEventRepository;
import com.example.BookCategoryPart.rest.BookCategoryRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookCategoryService
{
    @Value("${reader.part.url}")
    private String readerPartUrl;
    private BookCategoryRepository bookCategoryRepository;

    private final BookCategoryEventRepository eventRepository;

    private final RestTemplate restTemplate;
    private final BookCategoryRestRepository restRepository;

    @Autowired
    public BookCategoryService(BookCategoryRepository bookCategoryRepository, BookCategoryEventRepository eventRepository, RestTemplate restTemplate, BookCategoryRestRepository restRepository)
    {
        this.bookCategoryRepository = bookCategoryRepository;
        this.eventRepository = eventRepository;
        this.restTemplate = restTemplate;
        this.restRepository = restRepository;
    }

    public BookCategory saveCategory(BookCategory bookCategory)
    {
        BookCategory savedCategory = bookCategoryRepository.save(bookCategory);

        restRepository.notifyCategoryCreated(savedCategory.getId(), savedCategory.getName(), savedCategory.getPopularityScore());

        return savedCategory;
    }

    public Optional<BookCategory> getBookCategoryById(UUID categoryId)
    {
        return Optional.ofNullable(bookCategoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found")));
    }

    public BookCategoryReadDTO addCategory(BookCategoryCreateUpdateDTO bookCategoryDTO)
    {
        BookCategory category = new BookCategory();
        category.setId(UUID.randomUUID());
        category.setName(bookCategoryDTO.getName());
        category.setPopularityScore(bookCategoryDTO.getPopularityScore());

        restRepository.notifyCategoryCreated(category.getId(), category.getName(), category.getPopularityScore());

        bookCategoryRepository.save(category);

        return mapToBookCategoryReadDTO(category);
    }

    private BookCategoryReadDTO mapToBookCategoryReadDTO(BookCategory category)
    {
        BookCategoryReadDTO dto = new BookCategoryReadDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setPopularityScore(category.getPopularityScore());
        return dto;
    }

    public List<BookCategoryReadDTO> getAllCategoriesAsReadDTO() {
        List<BookCategory> categories = bookCategoryRepository.findAll();
        return categories.stream()
                .map(category -> new BookCategoryReadDTO(
                        category.getId(),
                        category.getName(),
                        category.getPopularityScore()
                ))
                .collect(Collectors.toList());
    }

    public Optional<BookCategoryReadDTO> getBookCategoryByIdDTO(UUID categoryId)
    {
        return bookCategoryRepository.findById(categoryId).map(this::mapToBookCategoryReadDTO);
    }

    public Optional<BookCategoryCollectionDTO> deleteCategoryDTO(UUID categoryID)
    {
        BookCategory bookCategory = bookCategoryRepository.findById(categoryID)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        BookCategoryCollectionDTO categoryCollectionDTO = new BookCategoryCollectionDTO();
        categoryCollectionDTO.setId(bookCategory.getId());
        categoryCollectionDTO.setName(bookCategory.getName());

        restRepository.notifyCategoryDeleted(categoryID);

        bookCategoryRepository.delete(bookCategory);

        return Optional.of(categoryCollectionDTO);
    }

    public Optional<BookCategoryReadDTO> updateCategoryDTO(UUID id, BookCategoryCreateUpdateDTO bookCategoryCreateUpdateDTO)
    {
        Optional<BookCategory> categoryToUpdate = getBookCategoryById(id);

        if(categoryToUpdate.isPresent())
        {
            BookCategory category = categoryToUpdate.get();
            category.setName(bookCategoryCreateUpdateDTO.getName());
            category.setPopularityScore(bookCategoryCreateUpdateDTO.getPopularityScore());

            BookCategory updatedCategory = bookCategoryRepository.save(category);

            BookCategoryReadDTO categoryReadDTO = new BookCategoryReadDTO(updatedCategory.getId(), updatedCategory.getName(), updatedCategory.getPopularityScore());
            return Optional.of(categoryReadDTO);

        }
        else
        {
            System.out.println("No category with id: " + id);
            return Optional.empty();
        }
    }

    public void handleCategoryCreation(String categoryName)
    {
        if(!bookCategoryRepository.findByName(categoryName).isPresent())
        {
            BookCategory bookCategory = new BookCategory();
            bookCategory.setId(UUID.randomUUID());
            bookCategory.setName(categoryName);
            bookCategory.setPopularityScore(0);
            bookCategoryRepository.save(bookCategory);
        }
    }

    public void handleCategoryDeletion(String categoryName)
    {
        bookCategoryRepository.findByName(categoryName).ifPresent(bookCategoryRepository::delete);
    }

}
