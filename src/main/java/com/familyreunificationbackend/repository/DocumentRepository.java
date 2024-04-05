package com.familyreunificationbackend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.familyreunificationbackend.model.Document;

public interface DocumentRepository extends JpaRepository<Document,UUID> {

}
