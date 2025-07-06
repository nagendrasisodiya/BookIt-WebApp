package org.spring.librarymanagmentsystemrestapi.controller.DTO;

import java.time.LocalDate;

public class IssuedBookDetailDTO {
    private Integer bookId;
    private String title;
    private String author;
    private String category;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public IssuedBookDetailDTO(Integer bookId, String title,
                               String author, String category,
                               LocalDate issueDate, LocalDate returnDate) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

}
