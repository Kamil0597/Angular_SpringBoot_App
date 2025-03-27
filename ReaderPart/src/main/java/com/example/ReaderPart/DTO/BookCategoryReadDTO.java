package com.example.ReaderPart.DTO;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookCategoryReadDTO
{
    private UUID id;
    private String name;
    private int popularityScore;
}
