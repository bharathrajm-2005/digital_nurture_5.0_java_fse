package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    public void saveBook(String title) {
        System.out.println("Saving book: " + title);
    }
}
