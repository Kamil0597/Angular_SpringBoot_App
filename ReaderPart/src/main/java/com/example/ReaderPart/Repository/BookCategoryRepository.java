package com.example.ReaderPart.Repository;
import com.example.ReaderPart.Entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, UUID>
{
    Optional<BookCategory> findByName(String name);
}
