package com.familyreunificationbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.familyreunificationbackend.dto.ChangePasswordDTO;
import com.familyreunificationbackend.input.CustomerInput;
import com.familyreunificationbackend.model.pagination.CustomerPage;
import com.familyreunificationbackend.model.paginationDefinition.PaginationInput;
import com.familyreunificationbackend.services.CustomerServices;

@Controller
public class CustomerController {
    @Autowired
    private CustomerServices userServices;

    @MutationMapping()
    public ResponseEntity<String> saveCustomer(@Argument(name = "customerInput") CustomerInput customerInput) {
        return userServices.saveOrUpdateCustomer(customerInput);
    }

    @MutationMapping()
    public ResponseEntity<String> deleteCustomer(@Argument(name = "id") long id) {
        return userServices.deleteCustomer(id);
    }
    public ResponseEntity<String>changeCustomerPassword(@Argument(name = "changePasswordInput")ChangePasswordDTO changePassword){
        return userServices.changeCustomerPassword(changePassword);
    }
    @QueryMapping()
    public CustomerPage customerPage(@Argument(name = "customerPage") PaginationInput paginationInput) {
        return userServices.customerPage(paginationInput);
    }
}
