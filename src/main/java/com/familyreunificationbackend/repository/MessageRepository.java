package com.familyreunificationbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.familyreunificationbackend.model.Message;

public interface MessageRepository extends JpaRepository<Message,Long>{
}
