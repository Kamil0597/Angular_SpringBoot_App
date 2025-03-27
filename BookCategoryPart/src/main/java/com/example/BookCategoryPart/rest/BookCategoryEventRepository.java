package com.example.BookCategoryPart.rest;

import java.util.UUID;

public interface BookCategoryEventRepository
{
    void notifyCategoryCreated(UUID id, String name, int popularityScore);
    void notifyCategoryDeleted(UUID id);
}
