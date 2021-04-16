package com.company.booksystemservice.viewmodel;

import com.company.booksystemservice.util.messages.NoteEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookViewModel {

    private Integer id;
    private String title;
    private String author;
    private List<NoteEntry> noteEntries;

}
