package com.example.ReaderPart.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@Builder
public class ReaderDto implements Comparable<ReaderDto>
{

    private String name;

    private int borrowCount;

    private String bookCategory;

    @Override
    public int hashCode()
    {
        return Objects.hash(name, borrowCount, bookCategory);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReaderDto readerDto = (ReaderDto) o;

        if (this.hashCode() != readerDto.hashCode())
            return false;

        return this.borrowCount == readerDto.borrowCount && Objects.equals(name, readerDto.name) && Objects.equals(bookCategory, readerDto.bookCategory);
    }
    @Override
    public String toString()
    {
        return "Character{name = " + name + ", borrow count = " + borrowCount + " book category = " + bookCategory + "}";
    }

    @Override
    public int compareTo(ReaderDto other) {
        return Integer.compare(this.borrowCount, other.borrowCount); // Sortowanie po poziomie
    }
}
