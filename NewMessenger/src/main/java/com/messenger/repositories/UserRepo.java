package com.messenger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messenger.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
