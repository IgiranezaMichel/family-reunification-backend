package com.familyreunificationbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.repository.GroupRepository;

@Service
public class GroupServices {
    @Autowired private GroupRepository groupRepository;
}
