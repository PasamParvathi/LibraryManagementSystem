package lms.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lms.library.entity.*;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findById(Long id);


}
