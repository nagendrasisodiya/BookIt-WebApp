package org.spring.librarymanagmentsystemrestapi.controller;

import org.spring.librarymanagmentsystemrestapi.controller.DTO.IssuedBookDetailDTO;
import org.spring.librarymanagmentsystemrestapi.controller.DTO.RemoteBookRequestDTO;
import org.spring.librarymanagmentsystemrestapi.model.AppUser;
import org.spring.librarymanagmentsystemrestapi.model.IssuedBook;
import org.spring.librarymanagmentsystemrestapi.service.AppUserService;
import org.spring.librarymanagmentsystemrestapi.service.BookService;
import org.spring.librarymanagmentsystemrestapi.service.IssuedBookService;
import org.spring.librarymanagmentsystemrestapi.service.RemoteBookRequestService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final BookService bookService;
    private final AppUserService appUserService;
    private final IssuedBookService issuedBookService;
    private final RemoteBookRequestService remoteBookRequestService;
    public UserController(BookService bookService, AppUserService appUserService,
                          IssuedBookService issuedBookService, RemoteBookRequestService remoteBookRequestService) {
        this.bookService = bookService;
        this.appUserService = appUserService;
        this.issuedBookService = issuedBookService;
        this.remoteBookRequestService = remoteBookRequestService;
    }
    @GetMapping("/issuedBook")
    public List<IssuedBookDetailDTO> issuedBook(@AuthenticationPrincipal UserDetails userDetails) {
            AppUser user= appUserService.getUserByEmail(userDetails.getUsername());
            List<IssuedBook> issuedBooks = issuedBookService.getIssuedBookByUser(user);
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
    @PostMapping("/requestbook")
    public void setRemoteBookRequests(@RequestBody RemoteBookRequestDTO remoteBookRequestDTO,
                                      @AuthenticationPrincipal UserDetails userDetails) {
        AppUser user= appUserService.getUserByEmail(userDetails.getUsername());
        remoteBookRequestService.addRemoteBookRequest(remoteBookRequestDTO, user);
    }
}
