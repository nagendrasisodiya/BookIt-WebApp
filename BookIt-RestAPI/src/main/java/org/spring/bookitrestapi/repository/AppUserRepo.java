package org.spring.bookitrestapi.repository;

import org.spring.bookitrestapi.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Integer> {
   public  AppUser findByEmail(String email);
}
