package com.example.BookCategoryPart.Controller;


import com.example.BookCategoryPart.DTO.BookCategoryCreateUpdateDTO;
import com.example.BookCategoryPart.DTO.BookCategoryReadDTO;
import com.example.BookCategoryPart.Entity.BookCategory;
import com.example.BookCategoryPart.Service.BookCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class BookCategoryController
{
    private final BookCategoryService bookCategoryService;

    public BookCategoryController(BookCategoryService bookCategoryService) {
        this.bookCategoryService = bookCategoryService;
    }

    @PostMapping
    public ResponseEntity<BookCategoryReadDTO> createBookCategory(@RequestBody BookCategoryCreateUpdateDTO bookCategoryDTO)
    {
        BookCategoryReadDTO createCategory = bookCategoryService.addCategory(bookCategoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookCategoryReadDTO> updateBookCategory(@PathVariable UUID id, @RequestBody BookCategoryCreateUpdateDTO bookCategoryCreateUpdateDTO)
    {
        return bookCategoryService.updateCategoryDTO(id, bookCategoryCreateUpdateDTO).map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookCategoryReadDTO> getBookCategoryById(@PathVariable UUID id)
    {
        return bookCategoryService.getBookCategoryByIdDTO(id).map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<BookCategoryReadDTO>> getAllCategoriesToRead()
    {
        List<BookCategoryReadDTO> categories = bookCategoryService.getAllCategoriesAsReadDTO();
        System.out.println("Kategorie zwrócone z backendu: " + categories);
        return ResponseEntity.ok(categories);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookCategoryById(@PathVariable UUID id)
    {
        if(bookCategoryService.deleteCategoryDTO(id).isPresent())
        {
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
