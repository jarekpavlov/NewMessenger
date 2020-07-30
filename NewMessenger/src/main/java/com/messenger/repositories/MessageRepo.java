package com.messenger.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messenger.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {

	List <Message> findByTag(String tag);

}
