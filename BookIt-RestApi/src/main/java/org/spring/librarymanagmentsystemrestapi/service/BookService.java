package org.spring.librarymanagmentsystemrestapi.service;

import org.spring.librarymanagmentsystemrestapi.model.AppUser;
import org.spring.librarymanagmentsystemrestapi.model.Book;
import org.spring.librarymanagmentsystemrestapi.repository.AppUserRepo;
import org.spring.librarymanagmentsystemrestapi.repository.BookRepo;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {
    private final BookRepo bookRepo;
    private final AppUserRepo appUserRepo;
    private final IssuedBookService issuedBookService;
    private final MailService mailService;
    public BookService(BookRepo bookRepo , AppUserRepo appUserRepo,
                       IssuedBookService issuedBookService, MailService mailService) {
        this.bookRepo = bookRepo;
        this.appUserRepo = appUserRepo;
        this.issuedBookService = issuedBookService;
        this.mailService = mailService;
    }
    public Book getBookById(int id) {
        return bookRepo.findById(id).get();
    }
    public void addBook(Book book) {
        int i=book.getQuantity();
        book.setAvailable(i);
        bookRepo.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }
    //assigning a book to a user
    public void assignBookToUser(Integer userId, Integer bookId) {
        AppUser appUser = appUserRepo.findById(userId)
                .orElseThrow(()->new RuntimeException("User Not Found"));
        Book book=bookRepo.findById(bookId)
                .orElseThrow(()->new RuntimeException("Book Not Found"));
        if(book.getAvailable()>0){
            issuedBookService.issueBook(book, appUser);
            book.setAvailable(book.getAvailable()-1);
            appUser.getBooks().add(book);
            bookRepo.save(book);
            appUserRepo.save(appUser);
        }else{
            throw new RuntimeException("Book Not Avialabile");
        }
    }
    //submit a book
    public void bookSubmission(Integer userId, Integer bookId) {
        System.out.println("calles for submissios");

        AppUser appUser=appUserRepo.findById(userId)
                .orElseThrow(()->new RuntimeException("User Not Found"));
        Book book=bookRepo.findById(bookId)
                .orElseThrow(()->new RuntimeException("Book Not Found"));
        book.setAvailable(book.getAvailable()+1);
        bookRepo.save(book);
        appUser.getBooks().remove(book);
        appUserRepo.save(appUser);
        issuedBookService.returnBook(book, appUser);
    }
}
