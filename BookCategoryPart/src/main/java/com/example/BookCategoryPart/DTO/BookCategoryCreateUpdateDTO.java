package com.example.BookCategoryPart.DTO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookCategoryCreateUpdateDTO
{
    private String name;
    private int popularityScore;
}
