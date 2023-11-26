package lms.library.repository;

import org.springframework.data.jpa.repository.*;

import lms.library.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	Admin findByAdminEmail(String adminEmail);
    Admin findByAdminId(Long adminId);
    
	
}