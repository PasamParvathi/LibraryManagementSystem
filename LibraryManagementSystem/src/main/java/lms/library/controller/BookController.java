package lms.library.controller;

import lms.library.entity.*;
import lms.library.exception.*;
import lms.library.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
    private BookService bookService;

	// Retrieves all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }
    
    // Finds a book by ID
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> findBookById(@PathVariable Long bookId) {
        try {
            Book book = bookService.findById(bookId); // Try to find the book
            return ResponseEntity.ok(book); // Return the book if found
        } catch (BookNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
           
        }
    }
    
    // Adds a new book
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.save(book);
    }

  //update book
  	 @PutMapping("/{bookId}")
  	    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) {

  	        Book updated = bookService.updateBook(bookId, updatedBook);

  	        if (updated != null) {
  	            return ResponseEntity.ok(updated);
  	        } else {
  	            return ResponseEntity.notFound().build(); 
  	        }
  	    }
    
  	 
    // Deletes a book by ID
    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long bookId) {
        try {
            bookService.deleteById(bookId); // Attempt to delete the book
            String message = "Book with ID: " + bookId + " deleted successfully.";
            return ResponseEntity.ok(message); // Return success message if successful
        } catch (BookNotAvailableException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book is currently borrowed and cannot be deleted");
         
        }
    }
    
 
    // Handles borrowing a book
    @PostMapping("/{bookId}/borrow/{userId}")
    public ResponseEntity<Book> borrowBook(@PathVariable Long bookId, @PathVariable Long userId) {
        Book borrowedBook = bookService.borrowBook(bookId, userId);
        if (borrowedBook != null) {
            return ResponseEntity.ok(borrowedBook);
        } else {
            return ResponseEntity.badRequest().build(); 
        }
    }
   
    
    // Handles returning a book
    @PostMapping("/{bookId}/return")
    public ResponseEntity<Book> returnBook(@PathVariable Long bookId) {
        Book returnedBook = bookService.returnBook(bookId);
        if (returnedBook != null) {
            return ResponseEntity.ok(returnedBook);
        } else {
            return ResponseEntity.badRequest().build(); 
        }
    }

    
}