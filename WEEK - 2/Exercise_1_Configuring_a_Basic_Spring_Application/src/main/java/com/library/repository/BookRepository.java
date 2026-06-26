package com.library.repository;

/**
 * WHY THIS CLASS EXISTS:
 * This is the Repository layer (also known as Data Access Object or DAO).
 * Its sole responsibility is to interact with the data source (like a database, in-memory store, or external API)
 * and perform CRUD (Create, Read, Update, Delete) operations on our entities (e.g., Book).
 *
 * DESIGN PATTERN - SEPARATION OF CONCERNS:
 * By separating the Repository (data access) from the Service (business logic), we ensure that the business logic
 * doesn't need to know *how* data is stored or retrieved. If we decide to switch from an in-memory database to
 * PostgreSQL, MongoDB, or a file storage system, we only need to modify or replace the Repository class.
 * The Service layer remains completely untouched!
 *
 * SPRING BEAN ROLE:
 * Spring will manage this class as a "Bean" (a managed Java object) in its Application Context. 
 * We do not create it manually using 'new BookRepository()'. Instead, Spring instantiates it and injects
 * it wherever it is needed (e.g., into BookService).
 */
public class BookRepository {

    /**
     * Simulates saving a book title.
     * In a real application, this method would use JDBC, Hibernate, JPA, or Spring Data
     * to save the book to a database.
     *
     * @param bookTitle The title of the book to save
     */
    public void saveBook(String bookTitle) {
        // We log to the console to verify that the execution flows from Main -> Service -> Repository
        System.out.println("[BookRepository] Successfully saved the book: '" + bookTitle + "' to the database.");
    }
}
