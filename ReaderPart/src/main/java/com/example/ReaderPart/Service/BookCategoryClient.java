package com.example.ReaderPart.Service;

import com.example.ReaderPart.DTO.BookCategoryReadDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCategoryClient
{
    private final RestTemplate restTemplate;


    public BookCategoryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri("http://category:8081").build();
    }

    public String getCategoryDetails(String categoryName)
    {
        String url = "http://category:8081/categories/details" + categoryName;
        try {
            return restTemplate.getForObject(url, String.class);
        }
        catch (Exception e)
        {
            return "Category not found in BookCategoryPart.";
        }
    }


    public List<String> fetchAllBookCategories() {
        try {
            ResponseEntity<List<BookCategoryReadDTO>> response = restTemplate.exchange(
                    "/api/categories",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<BookCategoryReadDTO>>() {}
            );

            List<BookCategoryReadDTO> categories = response.getBody();

            if(categories != null)
            {
                return categories.stream().map(BookCategoryReadDTO::getName).collect(Collectors.toList());
            }
            else
            {
                System.out.println("No categories in response");
                return Collections.emptyList();
            }
        } catch (Exception e) {
            System.err.println("Failed to fetch categories from BookCategoryPart: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
