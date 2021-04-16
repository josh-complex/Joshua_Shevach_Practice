package com.company.booksystemservice.util.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteEntry {

    private Integer id;
    @NotNull(message = "Must supply a book ID")
    private Integer bookId;
    @Size(max = 255, message = "Note must be 255 characters or less")
    private String note;

}
