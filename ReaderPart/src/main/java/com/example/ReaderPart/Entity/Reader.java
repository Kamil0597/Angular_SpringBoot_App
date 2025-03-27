package com.example.ReaderPart.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;



@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "readers")
public class Reader implements Comparable<Reader>, Serializable
{
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "borrow_count", nullable = false)
    private int borrowCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_category_id", nullable = false)
    private BookCategory bookCategory;

    @Override
    public int hashCode() {
        return Objects.hash(id, name, borrowCount, bookCategory);
    }

    @Override
    public String toString() {
        return "Reader{name = " + name + " | borrow count = " + borrowCount + " | book category = " + bookCategory.getName() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Reader reader = (Reader) o;
        return borrowCount == reader.borrowCount &&
                Objects.equals(id, reader.id) &&
                Objects.equals(name, reader.name) &&
                Objects.equals(bookCategory, reader.bookCategory);
    }

    @Override
    public int compareTo(Reader reader)
    {
        return Integer.compare(this.borrowCount, reader.borrowCount);
    }
}
