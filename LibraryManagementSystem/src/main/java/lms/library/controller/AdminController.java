package lms.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lms.library.entity.Admin;
import lms.library.exception.*;
import lms.library.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.findAll();
    }
    
    @GetMapping("/{adminId}")
    public Admin getAdmin(@PathVariable Long adminId) {
        return adminService.findById(adminId);
    }
   
 
    
    //update admin
  	@PutMapping("/{adminId}")
  	public ResponseEntity<Admin> updateAdmin(@PathVariable Long adminId, @RequestBody Admin updatedAdmin) {

		 	Admin updated = adminService.updateAdmin(adminId, updatedAdmin);

	        if (updated != null) {
	            return ResponseEntity.ok(updated);
	        } else {
	            return ResponseEntity.notFound().build(); 
	        }
	    }
    
    
    @DeleteMapping("/{adminId}")
    public void deleteAdmin(@PathVariable Long adminId) {
        adminService.deleteById(adminId);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        
    	Admin adminAdmin = new Admin(
                admin.getAdminPassword(),
                admin.getAdminEmail(),
                admin.getAdminName(),
                admin.getAdminPhone(),
                admin.getAdminAddress()
            );
    	
    	try {
            Admin registeredAdmin = adminService.registerAdmin(admin);
            return ResponseEntity.ok(registeredAdmin);
        } catch (EmailAlreadyRegisteredException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestParam String adminEmail, @RequestParam String adminPassword) {
        try {
            Admin admin=adminService.loginAdmin(adminEmail, adminPassword);
            return ResponseEntity.ok(admin);
        } catch (IncorrectPasswordException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
    
}