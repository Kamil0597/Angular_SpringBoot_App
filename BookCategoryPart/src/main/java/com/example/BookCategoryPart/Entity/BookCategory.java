package com.example.BookCategoryPart.Entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_categories")
public class BookCategory implements Comparable<BookCategory>, Serializable
{
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "popularity_score", nullable = false) // Nowa właściwość
    private int popularityScore;

    @Override
    public String toString() {
        return "BookCategory{id" + id + " | name: " + name + " | popularity Score: " + popularityScore + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, popularityScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BookCategory bookCategory = (BookCategory) o;
        return popularityScore == bookCategory.popularityScore &&
                Objects.equals(id, bookCategory.id) &&
                Objects.equals(name, bookCategory.name);
    }

    @Override
    public int compareTo(BookCategory p)
    {
        return Integer.compare(this.popularityScore, p.popularityScore);
    }
}
