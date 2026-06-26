package com.library;

import com.library.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * WHY THIS CLASS EXISTS:
 * This is the main entry point of our standalone Java application.
 * Its job is to bootstrap (start up) the Spring Application Context, retrieve the required beans,
 * and run our tests to demonstrate that the configuration works.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Starting Library Management Application (Basic Spring XML) ===");

        /*
         * WHY ClassPathXmlApplicationContext?
         * - 'ClassPathXmlApplicationContext' is a concrete implementation of the Spring 'ApplicationContext' interface.
         * - It loads XML-based configuration files from the application's classpath (such as target/classes or src/main/resources).
         * - By instantiating this class, we bootstrap the Spring IoC (Inversion of Control) container.
         *
         * WHY USE A TRY-WITH-RESOURCES block?
         * - ClassPathXmlApplicationContext implements 'AutoCloseable'.
         * - Using a try-with-resources block ensures that the Spring context is cleanly closed when the main method exits.
         * - Closing the context triggers Spring's bean destruction lifecycle methods (releasing resources, closing DB connections, etc.).
         */
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {

            System.out.println("\n[Spring IoC Container] Spring context loaded successfully!");
            System.out.println("[Spring IoC Container] Beans defined in context are initialized.");

            /*
             * RETRIEVING BEANS:
             * - Instead of instantiating BookService ourselves using:
             *     BookService service = new BookService();
             *   we ask the Spring container to retrieve it for us.
             *
             * - We pass "bookService" as the ID (matching the <bean id="bookService"> in applicationContext.xml).
             * - We pass 'BookService.class' to ensure type safety, so we don't have to cast the Object manually.
             */
            BookService bookService = context.getBean("bookService", BookService.class);

            System.out.println("\n[Main] Successfully fetched 'bookService' bean from Spring Context.");

            /*
             * RUNNING A TEST METHOD:
             * - We call 'registerBook' to demonstrate that BookService is fully wired.
             * - Because of Spring's Dependency Injection (DI), when we call registerBook, the Service
             *   will successfully invoke BookRepository without us ever having to set the dependency ourselves.
             */
            String bookTitle = "Design Patterns: Elements of Reusable Object-Oriented Software";
            System.out.println("[Main] Instructing BookService to register the book: '" + bookTitle + "'");
            bookService.registerBook(bookTitle);

        } catch (Exception e) {
            System.err.println("An error occurred during Spring Context initialization or execution:");
            e.printStackTrace();
        }

        System.out.println("\n=== Application Closed Cleanly ===");
    }
}
