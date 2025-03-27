package com.example.BookCategoryPart.Repository;
import com.example.BookCategoryPart.Entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, UUID>
{
    Optional<BookCategory> findByName(String name);

}
