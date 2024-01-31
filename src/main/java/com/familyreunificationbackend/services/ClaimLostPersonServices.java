package com.familyreunificationbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.repository.ClaimLostPersonRepository;

@Service
public class ClaimLostPersonServices {
    @Autowired private ClaimLostPersonRepository claimLostPersonRepository;
}
