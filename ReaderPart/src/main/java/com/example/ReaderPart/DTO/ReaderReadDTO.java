package com.example.ReaderPart.DTO;

import lombok.*;
import org.springframework.http.ResponseEntity;

import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReaderReadDTO
{
    private UUID id;
    private String name;
    private int borrowCount;
    private UUID bookCategoryId;
    private String bookCategoryName;

}
