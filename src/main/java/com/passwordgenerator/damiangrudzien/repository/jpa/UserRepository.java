package com.passwordgenerator.damiangrudzien.repository.jpa;

import com.passwordgenerator.damiangrudzien.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findUserByUsername(String username);

	boolean existsByUsername(String username);

	Optional<User> findByUsernameIgnoreCase(String username);
}
