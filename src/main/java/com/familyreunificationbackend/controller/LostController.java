package com.familyreunificationbackend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.familyreunificationbackend.dto.LostDTO;
import com.familyreunificationbackend.services.LostServices;
@Controller
public class LostController {
@Autowired private LostServices lostServices;
@MutationMapping
public ResponseEntity<String>saveLost(@Argument(name = "lost") LostDTO lostDTO){
    return lostServices.saveLost(lostDTO);
}
@MutationMapping
public ResponseEntity<String>deleteLost(@Argument(name = "id")UUID lostId){
    return lostServices.deleteLost(lostId);
}
}
