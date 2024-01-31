package com.familyreunificationbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.repository.ReceiverRepository;

@Service
public class ReceiverServices {
    @Autowired private ReceiverRepository receiverRepository;
}
