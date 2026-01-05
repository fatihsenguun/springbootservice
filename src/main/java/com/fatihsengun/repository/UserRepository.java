package com.fatihsengun.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatihsengun.entities.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
	Optional<User> findByUsername(String username);
	
}
