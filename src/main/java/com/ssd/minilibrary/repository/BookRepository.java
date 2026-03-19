package com.ssd.minilibrary.repository;

import com.ssd.minilibrary.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(String title, String category);
}
