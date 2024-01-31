package com.familyreunificationbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.repository.DocumentRepository;

@Service
public class DocumentServices {
    @Autowired private DocumentRepository documentRepository;
}
