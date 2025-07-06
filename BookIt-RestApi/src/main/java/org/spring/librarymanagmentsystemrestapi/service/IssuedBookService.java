package org.spring.librarymanagmentsystemrestapi.service;

import org.spring.librarymanagmentsystemrestapi.model.AppUser;
import org.spring.librarymanagmentsystemrestapi.model.Book;
import org.spring.librarymanagmentsystemrestapi.model.IssuedBook;
import org.spring.librarymanagmentsystemrestapi.repository.AppUserRepo;
import org.spring.librarymanagmentsystemrestapi.repository.IssuedBookRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
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
//    public void issueBook(Book book, AppUser appUser){
//        IssuedBook issuedBook = new IssuedBook();
//        issuedBook.setBook(book);
//        issuedBook.setUser(appUser);
//        issuedBook.setIssueDate(LocalDate.now());
//        issuedBook.setReturnDate(LocalDate.now().plusDays(5));
//        issuedBook.setBookName(book.getTitle());
//        issuedBookRepo.save(issuedBook);
//        //sending mail
//        String mailbody= mailService.genrateIssueMailBody(book.getTitle(),issuedBook.getReturnDate());
//        mailService.sendMail(
//                appUser.getEmail(),
//                "book issued by BookIt",
//                mailbody
//        );
//    }
public void issueBook(int sNo, int userId){
        Book book=bookService.getBookById(sNo);
        AppUser user=appUserService.getAppUserById(userId);
        if(book.getAvailable()>=1) {
            IssuedBook issuedBook = new IssuedBook();
            issuedBook.setBook(book);
            issuedBook.setUser(user);
            issuedBook.setIssueDate(LocalDate.now());
            issuedBook.setReturnDate(LocalDate.now().plusDays(4));
        }

}
    public void returnBook(Book book, AppUser appUser){
        IssuedBook issuedBook=issuedBookRepo.findByBookAndUser(book, appUser);
        issuedBookRepo.delete(issuedBook);
    }
}
