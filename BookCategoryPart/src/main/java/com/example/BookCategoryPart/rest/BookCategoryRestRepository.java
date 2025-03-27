package com.example.BookCategoryPart.rest;

import com.example.BookCategoryPart.Entity.CategoryNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Repository
public class BookCategoryRestRepository implements BookCategoryEventRepository
{
    private final RestTemplate restTemplate;

    @Autowired
    public BookCategoryRestRepository(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Override
    public void notifyCategoryCreated(UUID id, String name, int popularityScore) {
        String url = "http://localhost:8080/api/notifications/category/added"; // Adres API w ReaderPart
        CategoryNotification request = new CategoryNotification(id, name, popularityScore);


        try {
            restTemplate.postForEntity(url, request, Void.class);
            System.out.println("Notified ReaderPart: Category created with ID " + id);
        } catch (Exception e) {
            System.err.println("Failed to notify ReaderPart about category creation: " + e.getMessage());
        }
    }

    @Override
    public void notifyCategoryDeleted(UUID id) {
        String url = "http://localhost:8080/api/notifications/category/removed/" + id;

        try {
            restTemplate.delete(url);
            System.out.println("Notified ReaderPart: Category deleted with ID " + id);
        } catch (Exception e) {
            System.err.println("Failed to notify ReaderPart about category deletion: " + e.getMessage());
        }
    }
}
