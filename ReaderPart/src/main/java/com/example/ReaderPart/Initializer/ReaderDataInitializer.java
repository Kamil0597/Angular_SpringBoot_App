package com.example.ReaderPart.Initializer;

import com.example.ReaderPart.Entity.BookCategory;
import com.example.ReaderPart.Entity.Reader;
import com.example.ReaderPart.Service.BookCategoryClient;
import com.example.ReaderPart.Service.ReaderService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ReaderDataInitializer
{
    private final ReaderService readerService;
    private final BookCategoryClient bookCategoryClient;

    @Autowired
    public ReaderDataInitializer(ReaderService readerService, BookCategoryClient bookCategoryClient)
    {
        this.readerService = readerService;
        this.bookCategoryClient = bookCategoryClient;
    }

    @PostConstruct
    public void initData()
    {
         List<String> categories = new ArrayList<>();
        try {
            // Próba pobrania kategorii z BookCategoryPart
            categories = bookCategoryClient.fetchAllBookCategories();
        } catch (Exception e) {
            System.err.println("Failed to fetch categories from BookCategoryPart. Proceeding without categories.");
        }
        categories.forEach(categoryName -> readerService.handleCategoryCreation(categoryName));

        Optional<BookCategory> fantasyCategory = readerService.getCategoryByName("Fantasy");
        Reader reader = new Reader();
        reader.setId(UUID.randomUUID());
        reader.setName("John Doe");
        reader.setBorrowCount(5);
        if (fantasyCategory.isPresent()) {

            reader.setBookCategory(fantasyCategory.get());
            readerService.saveReader(reader);

            System.out.println("Reader 'John Doe' initialized with category 'Fantasy'.");
        } else
        {
            System.err.println("Category 'Fantasy' not found. Reader 'John Doe' created without category.");
        }
    }

}
