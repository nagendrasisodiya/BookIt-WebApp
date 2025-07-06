package org.spring.librarymanagmentsystemrestapi.controller;

import org.spring.librarymanagmentsystemrestapi.controller.DTO.UserBookDetailDTO;
import org.spring.librarymanagmentsystemrestapi.model.AppUser;
import org.spring.librarymanagmentsystemrestapi.model.Book;
import org.spring.librarymanagmentsystemrestapi.model.IssuedBook;
import org.spring.librarymanagmentsystemrestapi.service.AppUserService;
import org.spring.librarymanagmentsystemrestapi.service.BookService;
import org.spring.librarymanagmentsystemrestapi.service.IssuedBookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adminOnly")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
    private final AppUserService appUserService;
    private final BookService bookService;
    private final IssuedBookService issuedBookService;
    public AdminController(AppUserService appUserService, BookService bookService, IssuedBookService issuedBookService) {
        this.appUserService = appUserService;
        this.bookService = bookService;
        this.issuedBookService = issuedBookService;
    }
    @GetMapping("/getAllUsers")
    public List<AppUser> getAll() {
        return appUserService.getAllUsers();
    }
    @PostMapping("/addBook")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }
    @PostMapping("/assignBook")
    public void assignBook(@RequestParam int userId, @RequestParam int bookId) {
//        bookService.assignBookToUser(userId, bookId);

    }
    @PostMapping("/submitBook")
    public void submitBook( @RequestParam int userId, @RequestParam int bookId) {
        bookService.bookSubmission(userId, bookId);
    }
    @PostMapping("/removeUser")
    public void removeUser(@RequestParam int userId) {
        appUserService.removeUser(userId);
    }
    @GetMapping("/assignedBooks")
    public List<UserBookDetailDTO> getAllAssignedBooksWithDates() {
        List<AppUser> users = appUserService.getAllUsers();
        List<IssuedBook> allIssuedBooks = issuedBookService.getAllIssuedBooks();

        // Create a map of (userId, bookId) -> IssuedBook for quick lookup
        Map<String, IssuedBook> issuedBookMap = new HashMap<>();
        for (IssuedBook issuedBook : allIssuedBooks) {
            String key = issuedBook.getUser().getId() + "_" + issuedBook.getBook().getsNo();
            issuedBookMap.put(key, issuedBook);
        }

        List<UserBookDetailDTO> result = new ArrayList<>();

        for (AppUser user : users) {
            for (Book book : user.getBooks()) {
                String key = user.getId() + "_" + book.getsNo();
                IssuedBook issuedBook = issuedBookMap.get(key);

                LocalDate issueDate = null;
                LocalDate returnDate = null;

                if (issuedBook != null) {
                    issueDate = issuedBook.getIssueDate();
                    returnDate = issuedBook.getReturnDate();
                }

                UserBookDetailDTO dto = new UserBookDetailDTO(
                        user.getId(),
                        user.getUsername(),
                        book.getsNo(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getCategory(),
                        issueDate,
                        returnDate
                );

                result.add(dto);
            }
        }

        return result;
    }
}
