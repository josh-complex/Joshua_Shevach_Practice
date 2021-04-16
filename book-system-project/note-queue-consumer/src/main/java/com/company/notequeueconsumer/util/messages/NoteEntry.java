package com.company.notequeueconsumer.util.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteEntry {

    private Integer id;
    private Integer bookId;
    private String note;

}
