package org.spring.bookitrestapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;;


import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class IssuedBook {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    Integer uId;
    LocalDate issueDate;
    LocalDate returnDate;
    @ManyToOne
    @JoinColumn
    Book book;
    @ManyToOne
    @JoinColumn
    AppUser user;
    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
