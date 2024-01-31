package com.familyreunificationbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.repository.LostPersonDescriptionDetailRepository;

@Service
public class LostPersonDescriptionDetailRepositoryServices {
    @Autowired private LostPersonDescriptionDetailRepository lpddRepository;
}
