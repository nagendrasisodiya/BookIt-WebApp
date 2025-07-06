package org.spring.librarymanagmentsystemrestapi.repository;

import org.spring.librarymanagmentsystemrestapi.model.RemoteBookRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemoteBookRequestRepo extends JpaRepository<RemoteBookRequests, Integer> {
    List<RemoteBookRequests> findByStatusFalse();
}
