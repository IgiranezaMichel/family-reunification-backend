package com.familyreunificationbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.model.Customer;
import com.familyreunificationbackend.repository.ClaimLostPersonRepository;

@Service
public class ClaimLostPersonServices {
    @Autowired private ClaimLostPersonRepository claimLostPersonRepository;
    public ResponseEntity<String> saveOrUpdate(Customer user){
        claimLostPersonRepository.save(null);
        return new ResponseEntity<>("",HttpStatus.OK);
    }
}
