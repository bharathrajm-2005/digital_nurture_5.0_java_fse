package com.library.service;

import com.library.repository.BookRepository;

/**
 * WHY THIS CLASS EXISTS:
 * This is the Service layer. Its responsibility is to orchestrate and execute business logic.
 * The Service layer sits between the presentation layer (e.g., controllers/consoles) and the data access layer (Repository).
 *
 * DESIGN PATTERN - DEPENDENCY INJECTION (DI):
 * Notice that BookService depends on BookRepository to save data.
 * Instead of creating it inside this class like:
 *     private BookRepository bookRepository = new BookRepository();
 * we define a private field and provide a setter method (or constructor) for it.
 *
 * WHY NOT USE "new BookRepository()"?
 * 1. Hard Coupling: If we use "new", BookService is tightly coupled to the concrete class BookRepository.
 *    If we want to swap it with a MockBookRepository for testing, we can't do so easily.
 * 2. Lifecycle Management: With "new", BookService controls when BookRepository is created.
 *    With Spring's Inversion of Control (IoC), Spring manages the creation, lifetime, and configuration
 *    of both BookService and BookRepository, and wires them together.
 *
 * INJECTION TYPE: SETTER INJECTION
 * In this class, we use "Setter Injection" (a type of Dependency Injection).
 * Spring will look at the XML configuration, see that BookService has a property "bookRepository",
 * and call the setBookRepository(...) method on the BookService bean, passing the BookRepository bean.
 *
 * Alternative: Constructor Injection
 * We could also write a constructor:
 *     public BookService(BookRepository bookRepository) { ... }
 * Constructor Injection is generally preferred in modern Spring because it makes dependencies mandatory
 * and allows fields to be 'final' (immutable). However, Setter Injection is excellent for optional dependencies
 * and is extremely straightforward to demonstrate in Spring XML configurations.
 */
public class BookService {

    private BookRepository bookRepository;

    /**
     * Setter method for dependency injection.
     * Spring's IoC container calls this method to inject the BookRepository bean.
     *
     * @param bookRepository The BookRepository bean to inject
     */
    public void setBookRepository(BookRepository bookRepository) {
        System.out.println("[BookService] Injecting dependency: BookRepository was passed via setter injection.");
        this.bookRepository = bookRepository;
    }

    /**
     * Business method to register a new book in the library.
     *
     * @param title The title of the book to register
     */
    public void registerBook(String title) {
        System.out.println("[BookService] Starting book registration business logic for title: '" + title + "'...");
        // Delegate the actual persistence/storage to the repository layer
        bookRepository.saveBook(title);
    }
}
