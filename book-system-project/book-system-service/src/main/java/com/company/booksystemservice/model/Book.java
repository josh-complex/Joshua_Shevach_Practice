package com.company.booksystemservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Integer id;
    @NotBlank(message = "Must supply a title")
    @Size(max = 50, message = "Title must be 50 characters or less")
    private String title;
    @NotBlank(message = "Must supply an author")
    @Size(max = 50, message = "Author must be 50 characters or less")
    private String author;

}
