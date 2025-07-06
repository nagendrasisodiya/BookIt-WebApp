package org.spring.librarymanagmentsystemrestapi.controller;

import org.spring.librarymanagmentsystemrestapi.controller.DTO.IssuedBookDetailDTO;
import org.spring.librarymanagmentsystemrestapi.model.AppUser;
import org.spring.librarymanagmentsystemrestapi.model.Book;
import org.spring.librarymanagmentsystemrestapi.model.IssuedBook;
import org.spring.librarymanagmentsystemrestapi.model.RemoteBookRequests;
import org.spring.librarymanagmentsystemrestapi.service.AppUserService;
import org.spring.librarymanagmentsystemrestapi.service.BookService;
import org.spring.librarymanagmentsystemrestapi.service.IssuedBookService;
import org.spring.librarymanagmentsystemrestapi.service.RemoteBookRequestService;
import org.springframework.web.bind.annotation.*;

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
    private final RemoteBookRequestService remoteBookRequestService;
    public AdminController(AppUserService appUserService, BookService bookService,
                           IssuedBookService issuedBookService, RemoteBookRequestService remoteBookRequestService) {
        this.appUserService = appUserService;
        this.bookService = bookService;
        this.issuedBookService = issuedBookService;
        this.remoteBookRequestService = remoteBookRequestService;
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
        issuedBookService.issueBook(userId, bookId);
    }
    @PostMapping("/submitBook")
    public void submitBook( @RequestParam int userId, @RequestParam int bookId) {
        AppUser user=appUserService.getAppUserById(userId);
        Book book=bookService.getBookById(bookId);
        IssuedBook issuedBook = issuedBookService.getIssuedBookAndUser(user, book);
        issuedBookService.bookSubmission(issuedBook);
    }
    @PostMapping("/removeUser")
    public void removeUser(@RequestParam int userId) {
        appUserService.removeUser(userId);
    }
    @GetMapping("/assignedBooks")
    public List<IssuedBookDetailDTO> getAllAssignedBooksWithDates() {
        List<IssuedBook> issuedBooks = issuedBookService.getAllIssuedBooks();
        List<IssuedBookDetailDTO> issuedBookDetailDTOS = new ArrayList<>();
        for (IssuedBook issuedBook : issuedBooks) {
             IssuedBookDetailDTO dto=new IssuedBookDetailDTO(
                    issuedBook.getBook().getsNo(),
                    issuedBook.getBook().getTitle(),
                    issuedBook.getBook().getAuthor(),
                    issuedBook.getBook().getCategory(),
                    issuedBook.getIssueDate(),
                    issuedBook.getReturnDate(),
                    issuedBook.getUser().getId(),
                    issuedBook.getUser().getUsername()
            );
             issuedBookDetailDTOS.add(dto);
        }
        return issuedBookDetailDTOS;
    }
    @GetMapping("/getRemoteBookRequests")
    public List<Map<String, Object>> getRemoteBookRequests() {
        List<RemoteBookRequests> requests = remoteBookRequestService.getAllPendingRequests();
        List<Map<String, Object>> response = new ArrayList<>();

        for (RemoteBookRequests request : requests) {
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("requestId", request.getRequestId());
            requestMap.put("userId", request.getUser().getId());
            requestMap.put("userName", request.getUser().getUsername());
            requestMap.put("address", request.getAddress()); // You may need to add the address field to AppUser or get it from somewhere else
            requestMap.put("title", request.getBook().getTitle());
            requestMap.put("author", request.getBook().getAuthor());
            requestMap.put("bookId", request.getBook().getsNo());

            response.add(requestMap);
        }

        return response;
    }

    @PostMapping("/completeRemoteBookRequest")
    public void completeRemoteBookRequest(@RequestParam int requestId) {
        remoteBookRequestService.completeRequest(requestId);
    }
}
