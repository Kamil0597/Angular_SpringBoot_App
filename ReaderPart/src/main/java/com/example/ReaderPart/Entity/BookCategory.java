package com.example.ReaderPart.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_categories")
public class BookCategory implements Serializable {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "popularity_score", nullable = false)
    private int popularityScore;
}