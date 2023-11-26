package lms.library.service;

import lms.library.entity.*;
import lms.library.exception.*;
import lms.library.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class BookService {

	@Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    // Retrieves all books
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    
    // Finds a book by its ID; throws an exception if not found
    public Book findById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
        return book;
    }
    
    // Saves a book's information
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    
  //update a book
    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> existingBookOptional = bookRepository.findById(id);

        if (existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();
            
            // Update the existing librarian details with the new information
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setBorrowed(updatedBook.isBorrowed());
            existingBook.setBorrowedBy(updatedBook.getBorrowedBy());

            return bookRepository.save(existingBook);
        } else {
            return null;
        }
    }
    
    
    
    // Deletes a book by its ID, checking if it's available to delete
    public void deleteById(Long id) {
        Book book = findById(id); // Check if book exists before deleting
        if (book.isBorrowed()) {
            throw new BookNotAvailableException("Book is currently borrowed and cannot be deleted");
        }
        bookRepository.deleteById(id);
        displayDeletionConfirmation(id);
    }
    
    // Displays confirmation of deletion
    private void displayDeletionConfirmation(Long id) {
        System.out.println("Book with ID: " + id + " deleted successfully.");
        
    }
    
    // Borrows a book by a user, if available
    public Book borrowBook(Long bookId, Long userId) {
        Book book = findById(bookId);
        User user = userRepository.findById(userId).orElse(null);

        if (book != null && !book.isBorrowed() && user != null) {
            book.setBorrowedBy(user);
            book.setBorrowed(true);
            book.setBorrowedDate(new Date());
            return save(book);
        }
        
        return null;
    }
    
    
    public Book returnBook(Long bookId) {
        Book book = findById(bookId);
        if (book != null && book.isBorrowed()) {
            book.setBorrowedBy(null);
            book.setBorrowed(false);

            Date borrowedDate = book.getBorrowedDate();
            Date returnDate = new Date(); // Assuming return date as the current date

            BigDecimal fine = calculateFine(book, borrowedDate, returnDate);
            if (fine.compareTo(BigDecimal.ZERO) > 0) {
                System.out.println("Fine imposed: " + fine + " rupees.");
                // Handle the fine logic here, such as updating user balance or payment records
                // You may want to link this fine with a user's record or payment system
            } else {
                System.out.println("No fine incurred. Book returned successfully.");
            }

            return save(book);
        }
        return null;
    }

    private BigDecimal calculateFine(Book book, Date borrowedDate, Date returnDate) {
        // Existing fine calculation logic

        if (returnDate.after(borrowedDate)) {
            BigDecimal fine = BigDecimal.valueOf(0);

            long borrowedTimeInMillis = borrowedDate.getTime();
            long returnTimeInMillis = returnDate.getTime();
            long differenceInMillis = returnTimeInMillis - borrowedTimeInMillis;

            long daysDifference = differenceInMillis / (1000 * 60 * 60 * 24); // Convert milliseconds to days

            if (daysDifference > 30) {
                long extraDays = daysDifference - 30; // Calculate extra days beyond the first month
                long monthsLate = extraDays / 30; // Calculate months late
                fine = BigDecimal.valueOf(monthsLate * 20); // 20 rupees fine per month late
            }

            // Add bookPrice to the fine if the book is not returned
            if (returnDate.after(borrowedDate)) {
                fine = fine.add(book.getBookPrice());
            }

            return fine;
        }

        return BigDecimal.ZERO;
    }
    
}