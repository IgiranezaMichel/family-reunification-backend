package com.familyreunificationbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.repository.OrganizationRepository;

@Service
public class OrganizationServices {
    @Autowired private OrganizationRepository organizationRepository;
}
