package com.familyreunificationbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.familyreunificationbackend.model.Cases;
import com.familyreunificationbackend.services.CaseServices;

@Controller
public class CaseController {
    @Autowired
    private CaseServices caseServices;

    @MutationMapping
    public ResponseEntity<String> saveCases(@Argument(name = "caseInput") Cases cases,@Argument(name = "customerId")long customerId) {
        return caseServices.saveOrUpdate(cases,customerId);
    }

    @MutationMapping
    public ResponseEntity<String> deleteCases(@Argument(name = "id") long id) {
        return caseServices.deleteCase(id);
    }

    @QueryMapping
    public List<Cases> caseList() {
        return caseServices.caseList();
    }
}
