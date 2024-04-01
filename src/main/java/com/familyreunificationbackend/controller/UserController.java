package com.familyreunificationbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.familyreunificationbackend.input.UserInput;
import com.familyreunificationbackend.model.pagination.UserPage;
import com.familyreunificationbackend.model.paginationDefinition.PaginationInput;
import com.familyreunificationbackend.services.UserServices;

@Controller
public class UserController {
    @Autowired
    private UserServices userServices;

    @MutationMapping()
    public ResponseEntity<String> saveUser(@Argument(name = "userInput") UserInput userInput) {
        return userServices.saveOrUpdateUser(userInput);
    }

    @MutationMapping()
    public ResponseEntity<String> deleteUser(@Argument(name = "id") long id) {
        return userServices.deleteUser(id);
    }
    @QueryMapping()
    public UserPage userPage(@Argument(name = "userPage")PaginationInput paginationInput){
        return userServices.userPage(paginationInput);
    }
}
