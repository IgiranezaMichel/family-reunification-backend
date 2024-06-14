package com.familyreunificationbackend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.bytecode.enhance.spi.EnhancementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.input.OrganizationInput;
import com.familyreunificationbackend.model.Customer;
import com.familyreunificationbackend.model.Organization;
import com.familyreunificationbackend.model.pagination.OrganizationPage;
import com.familyreunificationbackend.model.paginationDefinition.PaginationInput;
import com.familyreunificationbackend.repository.CustomerRepository;
import com.familyreunificationbackend.repository.OrganizationRepository;

@Service
public class OrganizationServices {
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    public ResponseEntity<String> saveOrUpdateOrganization(OrganizationInput data) {
        data.setTimeStamp(LocalDateTime.now());
        Customer customer=customerRepository.findByUsername(data.getCustomerUserName());
        if(customer==null){
            return new ResponseEntity<>("Please add a valid User", HttpStatus.BAD_REQUEST);
        }
        Organization org = organizationRepository.save(new Organization(data.getId(), data.getName(),
                data.getBase64Logo(), data.getDescription(), data.getAddress(),customer));
        return new ResponseEntity<>(org.getName() + " organization saved successful", HttpStatus.OK);
    }

    public Organization findOrganizationById(long id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new EnhancementException("Organization not found"));
    }

    public ResponseEntity<String> deleteOrganization(long id) {
        try {
            Organization org = this.findOrganizationById(id);
            organizationRepository.delete(org);
            return new ResponseEntity<>(org.getName() + " removed successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Organization not found", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public OrganizationPage organizationPage(PaginationInput page) {
        Page<Organization> pagination = organizationRepository
                .findAll(PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by(page.getSort())));
        return new OrganizationPage(pagination.getNumber(), page.getPageSize(), pagination.getTotalElements(),
                pagination.getContent());
    }

    public List<Organization> organizationList() {
        return organizationRepository.findAll();
    }
}
