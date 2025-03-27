package com.example.ReaderPart.Controller;

import com.example.ReaderPart.DTO.ReaderCreateUpdateDTO;
import com.example.ReaderPart.DTO.ReaderReadDTO;
import com.example.ReaderPart.Service.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/readers")
public class ReaderController
{
    private final ReaderService readerService;

    public ReaderController(ReaderService readerService)
    {
        this.readerService = readerService;
    }

    @PostMapping
    public ResponseEntity<ReaderReadDTO> createReader(@RequestBody ReaderReadDTO readerDTO)
    {
        System.out.println("dodajemy czytelnika:" + readerDTO);
        ReaderReadDTO createdReader = readerService.addReader(readerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReader);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReaderReadDTO> getReaderById(@PathVariable UUID id)
    {
        return readerService.getReaderById(id).map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<ReaderReadDTO>> getAllReaders() {
        System.out.println("Wszedłem do metody getAllReaders");
        List<ReaderReadDTO> readers = readerService.getAllReadersAsReadDTO();

        // Debugowanie
        readers.forEach(reader -> System.out.println("Reader: " + reader));

        return ResponseEntity.ok(readers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReaderReadDTO> updateReaderById(@PathVariable UUID id, @RequestBody ReaderReadDTO readerDTO)
    {
        return readerService.updateReader(id, readerDTO).map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReaderById(@PathVariable UUID id)
    {
        if(readerService.deleteReaderDto(id).isPresent())
        {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/categories/{name}")
    public ResponseEntity<String> getCategoryDetails(@PathVariable String name)
    {
        String result = readerService.getCategoryDetails(name);
        return ResponseEntity.ok(result);
    }
}
