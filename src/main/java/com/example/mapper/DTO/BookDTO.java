package com.example.mapper.DTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class BookDTO {

    @NotEmpty(message = "Title is mandatory")
    private String title;

    @NotEmpty(message = "Author is mandatory")
    private String author;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive value")
    private int price;
}
