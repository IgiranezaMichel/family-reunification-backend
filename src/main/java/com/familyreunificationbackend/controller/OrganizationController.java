package com.familyreunificationbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.familyreunificationbackend.input.OrganizationInput;
import com.familyreunificationbackend.model.Organization;
import com.familyreunificationbackend.model.pagination.OrganizationPage;
import com.familyreunificationbackend.model.paginationDefinition.PaginationInput;
import com.familyreunificationbackend.services.OrganizationServices;

@Controller
public class OrganizationController {

    @Autowired
    private OrganizationServices organizationServices;

    @MutationMapping()
    public ResponseEntity<String> saveOrganization(@Argument(name = "organizationInput") OrganizationInput organization) {
        return organizationServices.saveOrUpdateOrganization(organization);
    }

    @MutationMapping()
    public ResponseEntity<String> removeOrganization(@Argument(name = "id") long id) {
        return organizationServices.deleteOrganization(id);
    }

    @QueryMapping()
    public OrganizationPage organizationPage(@Argument(name = "organizationPageInput") PaginationInput input) {
        return organizationServices.organizationPage(input);
    }
    @QueryMapping()
    public List<Organization> organizationList() {
        return organizationServices.organizationList();
    }
    
}
