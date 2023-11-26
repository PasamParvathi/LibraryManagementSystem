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
@RequestMapping("/api/users")
public class UserController {

	 @Autowired
	 private UserService userService;

	 
	 // Retrieves all users
	 @GetMapping
	 public List<User> getAllUsers() {
	     return userService.findAll();
	 }

	 // Adds a new user
	 @PostMapping
	 public User addUser(@RequestBody User user) {
	     return userService.save(user);
	 }
	   
	    
	// Finds a user by ID
	@GetMapping("/{userId}")
	public ResponseEntity<?> findUserById(@PathVariable Long userId) {
	     try {
	         User user = userService.findById(userId); // Try to find the user
	         return ResponseEntity.ok(user); // Return the user if found
	     } catch (UserNotFoundException ex) {
	            
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + userId);
	     }
	 }
	 
	
	// Updates user information
	@PutMapping("/{userId}")
    public ResponseEntity<User> updateUserById(
            @PathVariable Long userId,
            @RequestBody User updatedUser) {

        User updated = userService.updateUserById(userId, updatedUser);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            // Handle scenario if user with the given ID is not found
            return ResponseEntity.notFound().build();
        }
    }
	
	
	    
	// Deletes a user by ID
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
		userService.deleteById(userId);
	    	
	    String message = "User with ID: " + userId + " deleted successfully.";
	    return ResponseEntity.ok(message);
	}
	    
	    
	   


}