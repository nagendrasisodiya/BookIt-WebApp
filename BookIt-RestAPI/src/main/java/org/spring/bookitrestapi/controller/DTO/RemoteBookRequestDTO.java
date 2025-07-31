package org.spring.bookitrestapi.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RemoteBookRequestDTO {
    private String title;
    private String author;
    private String address;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getAddress() {
        return address;
    }
}
