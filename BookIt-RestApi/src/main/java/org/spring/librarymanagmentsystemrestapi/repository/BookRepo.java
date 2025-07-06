package org.spring.librarymanagmentsystemrestapi.repository;

import org.spring.librarymanagmentsystemrestapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Integer> {

}
