package com.woowoow.mayrose.repository;

import com.woowoow.mayrose.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
