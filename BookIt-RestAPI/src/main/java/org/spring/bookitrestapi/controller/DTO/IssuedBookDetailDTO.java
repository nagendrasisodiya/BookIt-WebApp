package org.spring.bookitrestapi.controller.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class IssuedBookDetailDTO {
    private Integer bookId;
    private String title;
    private String author;
    private String category;
    private Integer userId;
    private String userName;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public IssuedBookDetailDTO(Integer bookId, String title,
                               String author, String category,
                               LocalDate issueDate, LocalDate returnDate,
                               Integer userId, String userName) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.userId = userId;
        this.userName = userName;
    }
}
