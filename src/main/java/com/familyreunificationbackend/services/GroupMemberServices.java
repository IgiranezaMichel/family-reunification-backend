package com.familyreunificationbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.repository.GroupMemberRepository;

@Service
public class GroupMemberServices {
    @Autowired private GroupMemberRepository groupRepository;
}
