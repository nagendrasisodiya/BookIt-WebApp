package org.spring.bookitrestapi.repository;

import org.spring.bookitrestapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepo extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.title = :title AND b.author = :author")
    Book findByTitleAndAuthor(@Param("title") String title, @Param("author") String author);

}
