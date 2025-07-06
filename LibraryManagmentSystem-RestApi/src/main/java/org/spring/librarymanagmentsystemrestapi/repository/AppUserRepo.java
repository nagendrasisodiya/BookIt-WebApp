package org.spring.librarymanagmentsystemrestapi.repository;

import org.spring.librarymanagmentsystemrestapi.model.AppUser;
import org.spring.librarymanagmentsystemrestapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AppUserRepo extends JpaRepository<AppUser, Integer> {
   public  AppUser findByEmail(String email);
}
