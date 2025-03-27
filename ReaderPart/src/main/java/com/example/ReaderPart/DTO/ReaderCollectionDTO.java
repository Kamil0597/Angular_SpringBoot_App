package com.example.ReaderPart.DTO;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReaderCollectionDTO
{
    private UUID id;
    private String name;

}
