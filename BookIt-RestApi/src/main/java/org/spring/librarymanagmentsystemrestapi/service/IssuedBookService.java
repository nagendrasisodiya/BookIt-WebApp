package org.spring.librarymanagmentsystemrestapi.service;

import lombok.extern.slf4j.Slf4j;
import org.spring.librarymanagmentsystemrestapi.model.AppUser;
import org.spring.librarymanagmentsystemrestapi.model.Book;
import org.spring.librarymanagmentsystemrestapi.model.IssuedBook;
import org.spring.librarymanagmentsystemrestapi.repository.AppUserRepo;
import org.spring.librarymanagmentsystemrestapi.repository.IssuedBookRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class IssuedBookService {
    private final BookService bookService;
    private final AppUserService appUserService;
    private final IssuedBookRepo issuedBookRepo;
    private final MailService mailService;
    public IssuedBookService(IssuedBookRepo issuedBookRepo, MailService mailService,
                             AppUserService appUserService, BookService bookService) {
        this.issuedBookRepo = issuedBookRepo;
        this.mailService = mailService;
        this.appUserService = appUserService;
        this.bookService = bookService;
    }
    public List<IssuedBook> getAllIssuedBooks() {
        return issuedBookRepo.findAll();
    }
    public List<IssuedBook> getIssuedBookByUser(AppUser user) {
        return issuedBookRepo.findByUser(user);
    }
    public IssuedBook getIssuedBookAndUser(AppUser user, Book book) {
        return issuedBookRepo.findByBookAndUser(book, user);
    }
    public void issueBook(int userId, int sNo){
            Book book=bookService.getBookById(sNo);
            AppUser user=appUserService.getAppUserById(userId);
            if(book.getAvailable()>=1) {
                log.info("book available");
                IssuedBook issuedBook = new IssuedBook();
                issuedBook.setBook(book);
                issuedBook.setUser(user);
                issuedBook.setIssueDate(LocalDate.now());
                issuedBook.setReturnDate(LocalDate.now().plusDays(4));
                log.info("issuedBook to"+user.getId()+" "+issuedBook.getBook().getTitle());
                issuedBookRepo.save(issuedBook);
                //email notification
                String mailbody= mailService.genrateIssueMailBody(book.getTitle(),issuedBook.getReturnDate());
                mailService.sendMail(user.getEmail(),
                    "book issued by BookIt",
                    mailbody
            );
            }else{
                log.info("book :"+book.getTitle() +"not available cant issue book to user"+user.getId());
            }
    }
    public void bookSubmission(IssuedBook issuedBook) {
        issuedBookRepo.delete(issuedBook);
    }
}
