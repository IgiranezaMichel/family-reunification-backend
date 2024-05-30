package com.familyreunificationbackend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.familyreunificationbackend.model.Customer;
import com.familyreunificationbackend.services.CustomerServices;

import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
public class IndexController {

    @Autowired
    private CustomerServices customerServices;

    @PostMapping(value = "/login")
    public ResponseEntity<Customer> customerLogin(@RequestParam(name = "username",required = true) String userName) {
        try {
            Customer customer = customerServices.findByUsername(userName);
            log.info("customer",customer.getFirstName());
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }  catch (Exception e) {
            log.info("error", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/handle")
 public ResponseEntity<String> handle(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email) {
   return new ResponseEntity<String>("Hello World "+name+" "+email, HttpStatus.CREATED);
 }
}
