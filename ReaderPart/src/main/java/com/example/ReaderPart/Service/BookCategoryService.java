package com.example.ReaderPart.Service;

import com.example.ReaderPart.Entity.BookCategory;
import com.example.ReaderPart.Repository.BookCategoryRepository;

import java.util.UUID;

public class BookCategoryService
{
    private final BookCategoryRepository bookCategoryRepository;

    public BookCategoryService(BookCategoryRepository bookCategoryRepository) {
        this.bookCategoryRepository = bookCategoryRepository;
    }
}
