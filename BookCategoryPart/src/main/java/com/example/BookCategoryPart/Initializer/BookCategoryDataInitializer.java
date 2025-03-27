package com.example.BookCategoryPart.Initializer;

import com.example.BookCategoryPart.Entity.BookCategory;
import com.example.BookCategoryPart.Service.BookCategoryService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BookCategoryDataInitializer {

    private final BookCategoryService bookCategoryService;

    @Autowired
    public BookCategoryDataInitializer(BookCategoryService bookCategoryService) {
        this.bookCategoryService = bookCategoryService;
    }

    @PostConstruct
    public void initData() {
        BookCategory fantasyCategory = new BookCategory();
        fantasyCategory.setId(UUID.randomUUID());
        fantasyCategory.setName("Fantasy");
        fantasyCategory.setPopularityScore(100);
        bookCategoryService.saveCategory(fantasyCategory);

        BookCategory sciFiCategory = new BookCategory();
        sciFiCategory.setId(UUID.randomUUID());
        sciFiCategory.setName("Science Fiction");
        sciFiCategory.setPopularityScore(80);
        bookCategoryService.saveCategory(sciFiCategory);

        BookCategory historyCategory = new BookCategory();
        historyCategory.setId(UUID.randomUUID());
        historyCategory.setName("History");
        historyCategory.setPopularityScore(60);
        bookCategoryService.saveCategory(historyCategory);

        BookCategory mysteryCategory = new BookCategory();
        mysteryCategory.setId(UUID.randomUUID());
        mysteryCategory.setName("Mystery");
        mysteryCategory.setPopularityScore(70);
        bookCategoryService.saveCategory(mysteryCategory);

        System.out.println("Book categories have been initialized.");
    }
}