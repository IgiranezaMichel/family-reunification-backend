package com.familyreunificationbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.repository.OrganizationMemberRepository;
@Service
public class OrganizationMemberServices {
    @Autowired private OrganizationMemberRepository organizationMemberRepository;
}
