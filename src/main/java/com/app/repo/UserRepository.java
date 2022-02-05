package com.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query(" from User where email=:userEmail")
	Optional<User> findByEmail(String userEmail);
}
