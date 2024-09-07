package com.irctc.irctc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irctc.irctc.bean.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	Optional<User> findByUsernameAndPassword(String username, String password);
}
