package org.spring.librarymanagmentsystemrestapi.controller;

import org.spring.librarymanagmentsystemrestapi.model.AppUser;
import org.spring.librarymanagmentsystemrestapi.model.Book;
import org.spring.librarymanagmentsystemrestapi.service.AppUserService;
import org.spring.librarymanagmentsystemrestapi.service.BookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/common")
@CrossOrigin(origins = "http://localhost:4200")
public class CommonController {
    private final BookService bookService;
    private final AppUserService appUserService;

    public CommonController(BookService bookService, AppUserService appUserService) {
        this.bookService = bookService;
        this.appUserService = appUserService;
    }
    @GetMapping("/getProfile")
    public AppUser getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        AppUser appAdmin =appUserService.getUserByEmail(userDetails.getUsername());
        return appAdmin;
    }
    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

}
