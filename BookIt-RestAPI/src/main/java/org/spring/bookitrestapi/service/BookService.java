package org.spring.bookitrestapi.service;

import org.spring.bookitrestapi.model.Book;
import org.spring.bookitrestapi.repository.AppUserRepo;
import org.spring.bookitrestapi.repository.BookRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepo bookRepo;
    private final AppUserRepo appUserRepo;
    private final MailService mailService;
    public BookService(BookRepo bookRepo , AppUserRepo appUserRepo,
                    MailService mailService) {
        this.bookRepo = bookRepo;
        this.appUserRepo = appUserRepo;
        this.mailService = mailService;
    }
    public Book getBookById(int id) {
        Book book=bookRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Book Not Found"));
        return book;
    }
    public Book getBookByTitleAndAuthor(String title, String author){
        return bookRepo.findByTitleAndAuthor(title, author);
    }
    public void addBook(Book book) {
        int i=book.getQuantity();
        book.setAvailable(i);
        bookRepo.save(book);
    }
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }
}
