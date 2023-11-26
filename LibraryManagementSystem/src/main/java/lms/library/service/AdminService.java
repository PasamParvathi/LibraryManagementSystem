package lms.library.service;

import lms.library.entity.Admin;
import lms.library.entity.Librarian;
import lms.library.exception.*;
import lms.library.repository.AdminRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }
    
    public Admin findById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }

  //update an admin
    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        Optional<Admin> existingAdminOptional = adminRepository.findById(id);

        if (existingAdminOptional.isPresent()) {
        	Admin existingAdmin = existingAdminOptional.get();
            
            // Update the existing librarian details with the new information
            existingAdmin.setAdminEmail(updatedAdmin.getAdminEmail());
            existingAdmin.setAdminPassword(updatedAdmin.getAdminPassword());
            existingAdmin.setAdminName(updatedAdmin.getAdminName());
            existingAdmin.setAdminPhone(updatedAdmin.getAdminPhone());
            existingAdmin.setAdminAddress(updatedAdmin.getAdminAddress());

            return adminRepository.save(existingAdmin);
        } else {
            return null;
        }
    }
    
    
    public Admin registerAdmin(Admin admin) {
        // Check if the email is already registered
        Admin existingAdmin = adminRepository.findByAdminEmail(admin.getAdminEmail());
        if (existingAdmin != null) {
            throw new EmailAlreadyRegisteredException("Email is already registered.");
        }
        return adminRepository.save(admin);
    }

    public Admin loginAdmin(String adminEmail, String adminPassword) {
        Admin admin = adminRepository.findByAdminEmail(adminEmail);
        if (admin == null || !admin.getAdminPassword().equals(adminPassword)) {
            throw new IncorrectPasswordException("Incorrect email or password.");
        }
        return admin;
    }
}