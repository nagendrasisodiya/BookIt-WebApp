package org.spring.bookitrestapi.repository;

import org.spring.bookitrestapi.model.RemoteBookRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemoteBookRequestRepo extends JpaRepository<RemoteBookRequests, Integer> {
    List<RemoteBookRequests> findByStatusFalse();
}
