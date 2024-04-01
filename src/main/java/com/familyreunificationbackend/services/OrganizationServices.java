package com.familyreunificationbackend.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.model.Organization;
import com.familyreunificationbackend.model.pagination.OrganizationPage;
import com.familyreunificationbackend.model.paginationDefinition.PaginationInput;
import com.familyreunificationbackend.repository.OrganizationRepository;

@Service
public class OrganizationServices {
    @Autowired private OrganizationRepository organizationRepository;

    public ResponseEntity<String>saveOrUpdateOrganization(Organization data){
        data.setTimeStamp(LocalDateTime.now());
        Organization org=organizationRepository.save(data);
        return new ResponseEntity<>(org.getName()+" organization saved successful",HttpStatus.OK);
    }

    public Organization findOrganizationById(long id){
        return organizationRepository.findById(id).orElseThrow();
    }

    @SuppressWarnings("null")
    public ResponseEntity<String> deleteOrganization(long id){
        try {
            Organization org=this.findOrganizationById(id);
            organizationRepository.delete(org);
            return new ResponseEntity<>(org.getName()+" removed successful",HttpStatus.OK);
        } catch (Exception e) {
           return new ResponseEntity<>("Organization not found",HttpStatus.METHOD_NOT_ALLOWED);
        } 
    }

    public OrganizationPage organizationPage(PaginationInput page){
        Page<Organization>pagination=organizationRepository.findAll(PageRequest.of(page.getPageNumber(),page.getPageSize(),Sort.by(page.getSort())));
        return new OrganizationPage(pagination.getNumber(), page.getPageSize(), pagination.getTotalElements(), pagination.getContent());
    }
}
