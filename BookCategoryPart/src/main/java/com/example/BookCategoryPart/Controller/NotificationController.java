package com.example.BookCategoryPart.Controller;

import com.example.BookCategoryPart.Service.BookCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories/notifications")
public class NotificationController
{
    private final BookCategoryService bookCategoryService;

    public NotificationController(BookCategoryService bookCategoryService)
    {
        this.bookCategoryService = bookCategoryService;
    }

    @PostMapping("/notify-create")
    public ResponseEntity<Void> handleCategoryCreation(@RequestBody String categoryName)
    {
        bookCategoryService.handleCategoryCreation(categoryName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/notify-delete")
    public ResponseEntity<Void> handleCategoryDeletion(@RequestBody String categoryName)
    {
        bookCategoryService.handleCategoryDeletion(categoryName);
        return ResponseEntity.ok().build();
    }
}
