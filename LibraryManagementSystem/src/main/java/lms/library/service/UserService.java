package lms.library.service;

import lms.library.entity.*;
import lms.library.exception.*;
import lms.library.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;

	// Retrieves all users
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //Save a user
    public User save(User user) {
        return userRepository.save(user);
    }
        
    // Finds a user by ID; throws an exception if not found
    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        return user;
    }
    
    //update user by ID
    public User updateUserById(Long userId, User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(userId);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            // Update user fields with the new information
            existingUser.setUserName(updatedUser.getUserName());
            existingUser.setUserEmail(updatedUser.getUserEmail());
            existingUser.setUserPhone(updatedUser.getUserPhone());
            existingUser.setUserAddress(updatedUser.getUserAddress());

            return userRepository.save(existingUser);
        } else {
            
            return null;
        }
    }
    
    // Deletes a user by ID, after confirming its existence
    public void deleteById(Long id) {
    	User user = findById(id); // Check if user exists before deleting
    	
    	userRepository.deleteById(id);
        displayDeletionConfirmation(id);
    }
    
    // Displays confirmation of deletion
    private void displayDeletionConfirmation(Long id) {
        System.out.println("User with ID: " + id + " deleted successfully.");
        
    }
 
}