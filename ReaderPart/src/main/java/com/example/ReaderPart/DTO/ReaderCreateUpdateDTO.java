package com.example.ReaderPart.DTO;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReaderCreateUpdateDTO
{
    private String name;
    private int borrowCount;
    private UUID bookCategoryId;
}
