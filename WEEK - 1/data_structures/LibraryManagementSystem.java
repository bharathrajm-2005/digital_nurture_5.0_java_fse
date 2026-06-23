package data_structures;

import java.util.Arrays;
import java.util.Comparator;

/**
 * EXERCISE 6: Library Management System
 * 
 * 1. Search Algorithms:
 *    - Linear Search: Scans each item sequentially. Average O(N) complexity.
 *    - Binary Search: Splits search space in half repeatedly. Average O(log N) complexity.
 * 
 * 4. Complexity & Selection:
 *    - Linear Search: Better for small, unsorted, and dynamic catalogs.
 *    - Binary Search: Best for large, static, and sorted catalogs.
 */
class Book {
    private String bookId;
    private String title;
    private String author;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    @Override
    public String toString() {
        return "Book [ID=" + bookId + ", Title='" + title + "', Author='" + author + "']";
    }
}

public class LibraryManagementSystem {

    // Linear search by title
    public static Book linearSearch(Book[] books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Binary search by title (requires array sorted by title)
    public static Book binarySearch(Book[] books, String title) {
        int left = 0;
        int right = books.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = books[mid].getTitle().compareToIgnoreCase(title);

            if (comparison == 0) {
                return books[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Book[] books = {
            new Book("B01", "The Alchemist", "Paulo Coelho"),
            new Book("B02", "1984", "George Orwell"),
            new Book("B03", "Brave New World", "Aldous Huxley")
        };

        System.out.println("--- Linear Search ---");
        System.out.println("Found: " + linearSearch(books, "1984"));

        // Sort by Title for Binary Search
        Arrays.sort(books, Comparator.comparing(Book::getTitle));

        System.out.println("\n--- Binary Search (Sorted Array) ---");
        System.out.println("Found: " + binarySearch(books, "1984"));
    }
}
