package com.messenger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messenger.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {

}
