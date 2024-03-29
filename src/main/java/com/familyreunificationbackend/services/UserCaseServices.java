package com.familyreunificationbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.repository.CaseRepository;
@Service
public class UserCaseServices {
    @Autowired private CaseRepository caseRepository;
}
