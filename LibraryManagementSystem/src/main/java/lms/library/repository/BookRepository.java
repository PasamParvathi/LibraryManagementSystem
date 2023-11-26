package lms.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lms.library.entity.*;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	// Finds a librarian by their ID wrapped in an Optional
		Optional<Book> findById(Long id);

	
}