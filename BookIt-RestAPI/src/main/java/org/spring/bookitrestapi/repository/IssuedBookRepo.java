package org.spring.bookitrestapi.repository;

import org.spring.bookitrestapi.model.AppUser;
import org.spring.bookitrestapi.model.Book;
import org.spring.bookitrestapi.model.IssuedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IssuedBookRepo extends JpaRepository<IssuedBook, Integer> {
    @Query("SELECT ib FROM IssuedBook ib WHERE ib.book = :book AND ib.user = :user")
    IssuedBook findByBookAndUser(@Param("book") Book book, @Param("user") AppUser user);
     List<IssuedBook> findByUser(AppUser user);
     List<IssuedBook> findByReturnDate(LocalDate returnDate);
}
