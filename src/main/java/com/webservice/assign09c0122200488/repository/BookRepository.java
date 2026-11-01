package com.webservice.assign09c0122200488.repository;

import com.webservice.assign09c0122200488.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
