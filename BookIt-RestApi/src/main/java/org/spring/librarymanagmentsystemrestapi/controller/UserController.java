package org.spring.librarymanagmentsystemrestapi.controller;

import org.spring.librarymanagmentsystemrestapi.controller.DTO.IssuedBookDetailDTO;
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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final BookService bookService;
    private final AppUserService appUserService;
    private final IssuedBookService issuedBookService;
    public UserController(BookService bookService, AppUserService appUserService, IssuedBookService issuedBookService) {
        this.bookService = bookService;
        this.appUserService = appUserService;
        this.issuedBookService = issuedBookService;
    }
@GetMapping("/issuedBook")
public List<IssuedBookDetailDTO> issuedBook(@AuthenticationPrincipal UserDetails userDetails) {
    AppUser user= appUserService.getUserByEmail(userDetails.getUsername());
    Set<Book> books = user.getBooks();
    List<IssuedBook> issuedBooks = issuedBookService.getIssuedBookByUser(user);
    Map<Integer, IssuedBook> issuedBookMap = issuedBooks.stream()
            .collect(Collectors.toMap(
                    issuedBook -> issuedBook.getBook().getsNo(),
                    issuedBook -> issuedBook
            ));
    List<IssuedBookDetailDTO> bookDetails = new ArrayList<>();
    for (Book book : books) {
        IssuedBook issuedBook = issuedBookMap.get(book.getsNo());
        LocalDate issueDate = null;
        LocalDate returnDate = null;

        if (issuedBook != null) {
            issueDate = issuedBook.getIssueDate();
            returnDate = issuedBook.getReturnDate();
        }

        IssuedBookDetailDTO dto = new IssuedBookDetailDTO(
                book.getsNo(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                issueDate,
                returnDate
        );

        bookDetails.add(dto);
    }

    return bookDetails;
    }
}
