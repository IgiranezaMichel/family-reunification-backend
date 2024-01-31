package com.familyreunificationbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.repository.MessageRepository;
@Service
public class MessageServices {
    @Autowired private MessageRepository messageRepository;
}
