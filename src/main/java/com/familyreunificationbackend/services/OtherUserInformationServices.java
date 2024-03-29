package com.familyreunificationbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.repository.OtherUserInformationRepository;

@Service
public class OtherUserInformationServices {
    @Autowired private OtherUserInformationRepository otherUserInformationRepository;
}
