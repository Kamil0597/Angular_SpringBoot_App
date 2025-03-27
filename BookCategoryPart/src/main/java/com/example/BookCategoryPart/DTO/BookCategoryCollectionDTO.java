package com.example.BookCategoryPart.DTO;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookCategoryCollectionDTO
{
    private UUID id;
    private String name;
}
