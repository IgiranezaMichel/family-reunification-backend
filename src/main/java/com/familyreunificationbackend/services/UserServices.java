package com.familyreunificationbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.repository.UserRepository;
@Service
public class UserServices {
    @Autowired private UserRepository userRepository;
}
