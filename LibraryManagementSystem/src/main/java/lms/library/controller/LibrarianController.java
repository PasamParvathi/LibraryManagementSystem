package lms.library.controller;

import lms.library.entity.*;
import lms.library.exception.*;
import lms.library.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/librarians")
public class LibrarianController {

	 @Autowired
	 private LibrarianService librarianService;

	 //Retrieves all librarians
	 @GetMapping
	 public List<Librarian> getAllLibrarians() {
		 return librarianService.findAll();
	 }

	 // Adds a new librarian
	 @PostMapping
	 public Librarian addLibrarian(@RequestBody Librarian librarian) {
		 return librarianService.save(librarian);
	 }
	  
	 // Finds a librarian by ID  
	 @GetMapping("/{librarianId}")
	 public ResponseEntity<?> findLibrarianById(@PathVariable Long librarianId) {
	     try {
	         Librarian librarian = librarianService.findById(librarianId);
	         return ResponseEntity.ok(librarian);
	     } catch (LibrarianNotFoundException ex) {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                 .body("Librarian not found with ID: " + librarianId);
	     }
	 }
	
	 
	//update librarian
	 @PutMapping("/{librarianId}")
	    public ResponseEntity<Librarian> updateLibrarian(@PathVariable Long librarianId, @RequestBody Librarian updatedLibrarian) {

	        Librarian updated = librarianService.updateLibrarian(librarianId, updatedLibrarian);

	        if (updated != null) {
	            return ResponseEntity.ok(updated);
	        } else {
	            return ResponseEntity.notFound().build(); 
	        }
	    }
	
	 
	 // Deletes a librarian by ID
	 @DeleteMapping("/{librarianId}")
	 public ResponseEntity<String> deleteLibrarian(@PathVariable Long librarianId) {
		 librarianService.deleteById(librarianId);
	    	
	     String message = "Librarian with ID: " + librarianId + " deleted successfully.";
	     return ResponseEntity.ok(message);
	 }
	    
	 
	// Registers a new librarian
	 @PostMapping("/register")
	 public ResponseEntity<?> registerLibrarian(@RequestBody Librarian librarian) {
		 
		// Constructing a new Librarian object from the request
		 Librarian librarianLibrarian = new Librarian(
				 librarian.getLibrarianPassword(),
	             librarian.getLibrarianEmail(),
	             librarian.getLibrarianName(),
	             librarian.getLibrarianPhone(),
	             librarian.getLibrarianAddress()
		 );
	    	
	     try {
	    	Librarian registeredLibrarian = librarianService.registerLibrarian(librarian); // Here's the issue
	        return ResponseEntity.ok(registeredLibrarian);
	     } catch (EmailAlreadyRegisteredException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	     }
	 }

	// Handles the login process for a librarian
	 @PostMapping("/login")
	 public ResponseEntity<?> loginLibrarian(@RequestParam String librarianEmail, @RequestParam String librarianPassword) {
	     try {
	         Librarian librarian=librarianService.loginLibrarian(librarianEmail, librarianPassword);
	         return ResponseEntity.ok(librarian);
	     } catch (IncorrectPasswordException e) {
	         return ResponseEntity.status(401).body(e.getMessage());
	     }
	     
	 }
	 


}