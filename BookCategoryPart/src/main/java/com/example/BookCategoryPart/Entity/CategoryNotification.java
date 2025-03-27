package com.example.BookCategoryPart.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryNotification
{
    private UUID id;
    private String categoryName;
    private int popularityScore;
}