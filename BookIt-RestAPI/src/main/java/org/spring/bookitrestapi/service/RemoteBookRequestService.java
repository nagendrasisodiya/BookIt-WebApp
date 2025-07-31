package org.spring.bookitrestapi.service;

import org.spring.bookitrestapi.controller.DTO.RemoteBookRequestDTO;
import org.spring.bookitrestapi.model.AppUser;
import org.spring.bookitrestapi.model.Book;
import org.spring.bookitrestapi.model.RemoteBookRequests;
import org.spring.bookitrestapi.repository.RemoteBookRequestRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemoteBookRequestService {
    private RemoteBookRequestRepo remoteBookRequestRepo;
    private final BookService bookService;
    public RemoteBookRequestService(RemoteBookRequestRepo remoteBookRequestRepo, BookService bookService) {
        this.remoteBookRequestRepo = remoteBookRequestRepo;
        this.bookService = bookService;

    }
    public void addRemoteBookRequest(RemoteBookRequestDTO remoteBookRequestDTO, AppUser appUser) {
        RemoteBookRequests remoteBookRequests = new RemoteBookRequests();
        Book book=bookService.getBookByTitleAndAuthor(remoteBookRequestDTO.getTitle(), remoteBookRequestDTO.getAuthor());
        remoteBookRequests.setBook(book);
        remoteBookRequests.setUser(appUser);
        remoteBookRequests.setStatus(false);
        remoteBookRequests.setAddress(remoteBookRequestDTO.getAddress());
        remoteBookRequestRepo.save(remoteBookRequests);
    }
    public List<RemoteBookRequests> getAllPendingRequests() {
        return remoteBookRequestRepo.findByStatusFalse();
    }

    public void completeRequest(int requestId) {
        RemoteBookRequests request = remoteBookRequestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(true);
        remoteBookRequestRepo.save(request);
    }
}
