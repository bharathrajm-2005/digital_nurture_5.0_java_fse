package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;
    
    public BookService() {}
    
    public BookService(BookRepository bookRepository) {
        System.out.println("Constructor injection");
        this.bookRepository = bookRepository;
    }
    
    public void setBookRepository(BookRepository bookRepository) {
        System.out.println("Setter injection");
        this.bookRepository = bookRepository;
    }
    
    public void registerBook(String title) {
        bookRepository.saveBook(title);
    }
}
