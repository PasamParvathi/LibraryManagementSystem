package lms.library.service;

import lms.library.entity.*;
import lms.library.exception.*;
import lms.library.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LibrarianService {

	@Autowired
    private LibrarianRepository librarianRepository;

	//Retrieves all librarians
    public List<Librarian> findAll() {
        return librarianRepository.findAll();
    }

    // Saves a librarian
    public Librarian save(Librarian librarian) {
        return librarianRepository.save(librarian);
    }
   
    // Finds a librarian by ID; throws an exception if not found
    public Librarian findById(Long id) {
        Optional<Librarian> optionalLibrarian = librarianRepository.findById(id);
        
        if (optionalLibrarian.isPresent()) {
            return optionalLibrarian.get();
        } else {
            throw new LibrarianNotFoundException("Librarian not found with ID: " + id);
        }
    }
    
    //update a librarian
    public Librarian updateLibrarian(Long id, Librarian updatedLibrarian) {
        Optional<Librarian> existingLibrarianOptional = librarianRepository.findById(id);

        if (existingLibrarianOptional.isPresent()) {
            Librarian existingLibrarian = existingLibrarianOptional.get();
            
            // Update the existing librarian details with the new information
            existingLibrarian.setLibrarianEmail(updatedLibrarian.getLibrarianEmail());
            existingLibrarian.setLibrarianPassword(updatedLibrarian.getLibrarianPassword());
            existingLibrarian.setLibrarianName(updatedLibrarian.getLibrarianName());
            existingLibrarian.setLibrarianPhone(updatedLibrarian.getLibrarianPhone());
            existingLibrarian.setLibrarianAddress(updatedLibrarian.getLibrarianAddress());

            return librarianRepository.save(existingLibrarian);
        } else {
            return null;
        }
    }
    
    // Deletes a librarian by ID, after confirming its existence
    public void deleteById(Long id) {
    	Librarian  librarian= findById(id); // Check if user exists before deleting
    	librarianRepository.deleteById(id);
        displayDeletionConfirmation(id);
    }

    // Displays confirmation of deletion
    private void displayDeletionConfirmation(Long id) {
        System.out.println("Librarian with ID: " + id + " deleted successfully.");
        
    }
    
    // Registers a new librarian, throwing an exception if email already exists
    public Librarian registerLibrarian(Librarian librarian) {
        // Check if the email is already registered
    	Librarian existingLibrarian = librarianRepository.findByLibrarianEmail(librarian.getLibrarianEmail());
        if (existingLibrarian != null) {
            throw new EmailAlreadyRegisteredException("Email is already registered.");
        }
        return librarianRepository.save(librarian);
    }

    // Handles the login process for a librarian
    public Librarian loginLibrarian(String librarianEmail, String librarianPassword) {
    	Librarian librarian = librarianRepository.findByLibrarianEmail(librarianEmail);
        if (librarian == null || !librarian.getLibrarianPassword().equals(librarianPassword)) {
            throw new IncorrectPasswordException("Incorrect email or password.");
        }
        return librarian;
    }
 
}