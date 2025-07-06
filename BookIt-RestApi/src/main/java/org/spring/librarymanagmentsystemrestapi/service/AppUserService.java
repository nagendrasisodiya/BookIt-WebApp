package org.spring.librarymanagmentsystemrestapi.service;

import org.spring.librarymanagmentsystemrestapi.model.AppUser;
import org.spring.librarymanagmentsystemrestapi.model.Book;
import org.spring.librarymanagmentsystemrestapi.repository.AppUserRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AppUserService {
    private final AppUserRepo appUserRepo;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
    }
    public AppUser getAppUserById(int id) {
        return appUserRepo.findById(id).get();
    }
    public void addUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepo.save(appUser);
    }
    public List<AppUser> getAllUsers() {
        return appUserRepo.findAll();
    }
    public AppUser getUserByEmail(String email) {
        return appUserRepo.findByEmail(email);
    }
    public void removeUser(int id) {
        AppUser appUser = appUserRepo.findById(id).get();
        Set<Book> books= appUser.getBooks();
        for (Book book : books) {
            book.setAvailable(book.getAvailable()+1);
        }
        appUser.getBooks().clear();
        appUserRepo.save(appUser);
        appUserRepo.delete(appUser);
    }

}
