package com.example.ReaderPart.Service;

import com.example.ReaderPart.DTO.ReaderCollectionDTO;
import com.example.ReaderPart.DTO.ReaderCreateUpdateDTO;
import com.example.ReaderPart.DTO.ReaderDto;
import com.example.ReaderPart.DTO.ReaderReadDTO;
import com.example.ReaderPart.Entity.BookCategory;
import com.example.ReaderPart.Entity.Reader;
import com.example.ReaderPart.Repository.BookCategoryRepository;
import com.example.ReaderPart.Repository.ReaderRepository;
import lombok.Lombok;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReaderService
{
    private final ReaderRepository readerRepository;

    private final BookCategoryRepository bookCategoryRepository;

    private final BookCategoryClient bookCategoryClient;

    @Autowired
    public ReaderService(ReaderRepository readerRepository, BookCategoryRepository bookCategoryRepository, BookCategoryClient bookCategoryClient) {
        this.readerRepository = readerRepository;
        this.bookCategoryRepository = bookCategoryRepository;
        this.bookCategoryClient = bookCategoryClient;
    }

    public Reader saveReader(Reader reader)
    {
        return readerRepository.save(reader);
    }


    public String getCategoryDetails(String categoryName)
    {
        Optional<BookCategory> category = bookCategoryRepository.findByName(categoryName);

        if(category.isPresent())
        {
            return "Simplified Category: " + category.get().getName();
        }
        return bookCategoryClient.getCategoryDetails(categoryName);
    }


    public List<ReaderReadDTO> getAllReadersAsReadDTO()
    {
        return readerRepository.findAll().stream().map(reader ->
                        ReaderReadDTO.builder()
                                .id(reader.getId())
                                .name(reader.getName())
                                .borrowCount(reader.getBorrowCount())
                                .bookCategoryName(reader.getBookCategory().getName())
                                .build())
                .collect(Collectors.toList());
    }


    public ReaderReadDTO addReader(ReaderReadDTO readerDTO)
    {
        System.out.println("wszedłem do dodawania czytelnika");
        List<BookCategory> categories = bookCategoryRepository.findAll();
        for (BookCategory category : categories) {
            System.out.println("ID: " + category.getId() + ", Name: " + category.getName());
        }
        System.out.println(readerDTO.getBookCategoryId());
        BookCategory bookCategory = bookCategoryRepository.findById(readerDTO.getBookCategoryId())
                        .orElseThrow(() -> new IllegalArgumentException("Book category not found for ID: " + readerDTO.getBookCategoryId()));

        Reader reader = new Reader();
        reader.setId(UUID.randomUUID());
        reader.setName(readerDTO.getName());
        reader.setBorrowCount(readerDTO.getBorrowCount());
        reader.setBookCategory(bookCategory);

        Reader savedReader = readerRepository.save(reader);

        ReaderReadDTO result = new ReaderReadDTO();
        result.setId(savedReader.getId());
        result.setName(savedReader.getName());
        result.setBorrowCount(savedReader.getBorrowCount());
        result.setBookCategoryName(savedReader.getBookCategory().getName());
        return result;
    }

    public Optional<ReaderReadDTO> getReaderById(UUID id)
    {
        return readerRepository.findById(id).map(reader -> mapToReaderReadDTO(reader));
    }


    protected ReaderReadDTO mapToReaderReadDTO(Reader reader)
    {
        ReaderReadDTO readerReadDTO = new ReaderReadDTO();
        readerReadDTO.setId(reader.getId());
        readerReadDTO.setName(reader.getName());
        readerReadDTO.setBorrowCount(reader.getBorrowCount());
        readerReadDTO.setBookCategoryName(reader.getBookCategory().getName());
        System.out.println("Po mapowaniu: " + readerReadDTO.getId());
        return readerReadDTO;
    }

    public Optional<ReaderReadDTO> updateReader(UUID readerId, ReaderReadDTO readerDTO) {

        if (readerId == null) {
            throw new IllegalArgumentException("Reader ID must not be null");
        }


        Reader existingReader = readerRepository.findById(readerId)
                .orElseThrow(() -> new IllegalArgumentException("Reader not found for ID: " + readerId));


        BookCategory bookCategory = bookCategoryRepository.findByName(readerDTO.getBookCategoryName())
                .orElseThrow(() -> new IllegalArgumentException("Book category not found for name: " + readerDTO.getBookCategoryName()));


        existingReader.setName(readerDTO.getName());
        existingReader.setBorrowCount(readerDTO.getBorrowCount());
        existingReader.setBookCategory(bookCategory);


        Reader savedReader = readerRepository.save(existingReader);

        return Optional.of(mapToReaderReadDTO(savedReader));
    }

    @Transactional
    public List<Reader> getReadersByCategory(BookCategory bookCategory) {
        List<Reader> readers = readerRepository.findByBookCategory(bookCategory);

        readers.forEach(reader -> Hibernate.initialize(reader.getBookCategory()));

        return readers;
    }

    public void deleteReadersByCategoryName(String categoryName)
    {
        readerRepository.findAll().forEach(reader ->
                {
                    if(reader.getBookCategory().getName() == categoryName)
                    {
                        readerRepository.delete(reader);
                    }
                }
                );
    }

    public void handleCategoryCreation(String categoryName)
    {
        if(!bookCategoryRepository.findByName(categoryName).isPresent())
        {
            BookCategory bookCategory = new BookCategory();
            bookCategory.setId(UUID.randomUUID());
            bookCategory.setName(categoryName);
            bookCategoryRepository.save(bookCategory);
        }
    }

    public void handleCategoryCreation2(UUID id, String categoryName, int popularityScore)
    {
        if(!bookCategoryRepository.findByName(categoryName).isPresent())
        {
            BookCategory bookCategory = new BookCategory();
            bookCategory.setId(id);
            bookCategory.setName(categoryName);
            bookCategory.setPopularityScore(popularityScore);
            bookCategoryRepository.save(bookCategory);
        }
    }

    public void handleCategoryDeletion(UUID id)
    {
        bookCategoryRepository.findById(id).ifPresent(category ->
        {
            if (category.getId() == id) {
                deleteReadersByCategoryName(category.getName());
                bookCategoryRepository.delete(category);
            }
        });
    }

    public Optional<BookCategory> getCategoryByName(String categoryName)
    {
        return bookCategoryRepository.findByName(categoryName);
    }
    public List<BookCategory> getAllCategories()
    {
        return bookCategoryRepository.findAll();
    }

}
