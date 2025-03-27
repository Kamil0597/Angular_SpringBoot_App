package com.example.ReaderPart.Controller;

import com.example.ReaderPart.Entity.BookCategory;
import com.example.ReaderPart.Entity.CategoryNotification;
import com.example.ReaderPart.Service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
public class BookCategoryNotificationController
{
    private final ReaderService readerService;

    @Autowired
    public BookCategoryNotificationController(ReaderService readerService) {
        this.readerService = readerService;
    }


    @PostMapping("/category/added")
    public void handleCategoryAdded(@RequestBody CategoryNotification categoryNotification)
    {
        System.out.println(categoryNotification.getId() + " " + categoryNotification.getCategoryName() + " " + categoryNotification.getPopularityScore());
        readerService.handleCategoryCreation2(categoryNotification.getId(), categoryNotification.getCategoryName(), categoryNotification.getPopularityScore());
    }

    @DeleteMapping("/category/removed/{id}")
    public void handleCategoryRemoved(@PathVariable UUID id)
    {
        System.out.println();
        readerService.handleCategoryDeletion(id);
    }

    @GetMapping
    public List<BookCategory> getAllCategories() {
        return readerService.getAllCategories();
    }

}
