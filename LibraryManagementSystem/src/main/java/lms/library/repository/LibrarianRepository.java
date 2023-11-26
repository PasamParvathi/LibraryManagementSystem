package lms.library.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import lms.library.entity.*;

public interface LibrarianRepository extends JpaRepository<Librarian, Long>{
	
	// Finds a librarian by their email
	Librarian findByLibrarianEmail(String librarianEmail);
	
	// Finds a librarian by their ID wrapped in an Optional
	Optional<Librarian> findById(Long id);

}