package com.familyreunificationbackend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import java.util.List;
import com.familyreunificationbackend.dto.LostDTO;
import com.familyreunificationbackend.dto.LostPageDTO;
import com.familyreunificationbackend.dto.LostPageInput;
import com.familyreunificationbackend.model.Lost;
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
@QueryMapping
public List<Lost>lostList(){
    return lostServices.findAll();
}
@QueryMapping
public List<Lost>customerLostPosts(@Argument(name ="customerId" )long customerId){
    return lostServices.getCustomerLostPosts(customerId);
}
@QueryMapping
public  Lost findLostById(@Argument(name ="customerId" )UUID customerId){
    return lostServices.findById(customerId);
}
// admin
@QueryMapping
public LostPageDTO<Lost> lostPageable(@Argument(name = "input")LostPageInput lostPageInput){
    return lostServices.lostPageable(lostPageInput);
}
}
