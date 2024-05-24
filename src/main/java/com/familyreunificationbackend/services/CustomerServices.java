package com.familyreunificationbackend.services;

import java.util.Base64;

import org.hibernate.bytecode.enhance.spi.EnhancementException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.enums.Role;
import com.familyreunificationbackend.input.CustomerInput;
import com.familyreunificationbackend.model.Customer;
import com.familyreunificationbackend.model.pagination.CustomerPage;
import com.familyreunificationbackend.model.paginationDefinition.PaginationInput;
import com.familyreunificationbackend.repository.CustomerRepository;

@Service
public class CustomerServices {
    @Autowired
    private CustomerRepository userRepository;

    public ResponseEntity<String> saveOrUpdateCustomer(CustomerInput userInput) {
        try {
            if (!userInput.getBase64ProfilePicture().contains("base64,"))
                throw new Exception("Profile Picture is required");
            String imgs = userInput.getBase64ProfilePicture().split("base64,")[1];
            byte[] arr = Base64.getDecoder().decode(imgs);
            Customer user = new Customer(userInput.getId(), userInput.getFirstName(), userInput.getLastName(), arr,
                    userInput.getGender(), userInput.getEmail(), userInput.getPhoneNumber(), userInput.getDob(),
                    userInput.getAddress(), userInput.getCountry(), userInput.getNativeCountry(), Role.USER,
                    userInput.getUsername(), userInput.getPassword());
            Customer result = userRepository.save(user);
            if (userInput.getId() != 0)
                return new ResponseEntity<>("Hi " + result.getFirstName() + " Your Information has updated successful",
                        HttpStatus.OK);
            return new ResponseEntity<>("Hi " + result.getFirstName() + " Your Information has saved successful",
                    HttpStatus.OK);
        } catch (Exception exp) {
            if (exp instanceof ConstraintViolationException) {
                return new ResponseEntity<>("User Already exist", HttpStatus.METHOD_NOT_ALLOWED);
            }
            return new ResponseEntity<>("Incorrect information or Wrong Chosen Picture", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public Customer findCustomerById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new EnhancementException("User not found"));
    }

    public ResponseEntity<String> deleteCustomer(long id) {
        try {
            Customer user = this.findCustomerById(id);
            userRepository.delete(user);
            return new ResponseEntity<>(user.getFirstName() + " has deleted successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User not found", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public CustomerPage customerPage(PaginationInput page) {
        Page<Customer> pagination = userRepository
                .findAll(PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by(page.getSort())));
        return new CustomerPage(pagination.getNumber(), pagination.getTotalPages(), pagination.getTotalElements(),
                pagination.getContent());
    }
}
