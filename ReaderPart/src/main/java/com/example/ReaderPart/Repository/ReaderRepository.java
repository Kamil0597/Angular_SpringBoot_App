package com.example.ReaderPart.Repository;

import com.example.ReaderPart.Entity.BookCategory;
import com.example.ReaderPart.Entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, UUID>
{
    Optional<Reader> findByName(String name);
    List<Reader> findByBookCategory(BookCategory bookCategory);

    @Query("SELECT r FROM Reader r JOIN FETCH r.bookCategory WHERE r.bookCategory = :category")
    List<Reader> findReadersByBookCategoryWithCategory(@Param("category") BookCategory category);
}
