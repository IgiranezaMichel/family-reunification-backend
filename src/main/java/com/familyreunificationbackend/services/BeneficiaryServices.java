package com.familyreunificationbackend.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.familyreunificationbackend.enums.Reunify;
import com.familyreunificationbackend.model.Beneficiary;
import com.familyreunificationbackend.model.Customer;
import com.familyreunificationbackend.model.Lost;
import com.familyreunificationbackend.model.Organization;
import com.familyreunificationbackend.repository.BeneficiaryRepository;
import com.familyreunificationbackend.repository.CustomerRepository;
import com.familyreunificationbackend.repository.LostRepository;
import com.familyreunificationbackend.repository.OrganizationRepository;
import com.familyreunificationbackend.restbodyinput.BeneficiaryInput;
@Service
public class BeneficiaryServices {
@Autowired private BeneficiaryRepository beneficiaryRepository;
@Autowired private LostRepository lostRepository;
@Autowired private OrganizationRepository organizationRepository;
@Autowired private CustomerRepository customerRepository;
public ResponseEntity<String> create(BeneficiaryInput beneficiary){
    Lost lost=lostRepository.findById(beneficiary.getLostId()).orElse(null);
    Beneficiary beneficiary2=beneficiaryRepository.findByLost(lost);
    if(beneficiary2!=null) return new ResponseEntity<>("Missing person already exist",HttpStatus.NOT_ACCEPTABLE);
    Organization organization=organizationRepository.findById(beneficiary.getOrganizationId()).orElse(null);
    beneficiaryRepository.save(new Beneficiary(beneficiary.getId(), beneficiary.getReunification(), lost, organization, beneficiary.getDescription(),beneficiary.getCountryFound(),beneficiary.getAdress(),beneficiary.getCountryOfDestination(),beneficiary.getDestinationAddress(),beneficiary.getComment()));
    return new ResponseEntity<>("Claim Added successful",HttpStatus.CREATED);
}
public List<Beneficiary> getBeneficiaryListByReunify(Reunify reunifyStatus){
return beneficiaryRepository.findAllByReunification(reunifyStatus);
}
public List<Beneficiary> getCustomerBeneficiaries(String username){
    Customer customer=customerRepository.findByUsername(username);
    return beneficiaryRepository.findAllByLostPostedBy(customer);
}
public List<Beneficiary> getBeneficiaryOrganizationAndReunification(long organizationId,Reunify reunify){
    Organization organization=organizationRepository.findById(organizationId).orElse(null);
    return beneficiaryRepository.findAllByOrganizationAndReunification(organization,reunify);
}
public ResponseEntity<String> updateBeneficiary(UUID beneficiaryId, Reunify reunify, String comment) {
    try {
        Beneficiary beneficiary=beneficiaryRepository.findById(beneficiaryId).orElseThrow();
        beneficiary.setReunification(reunify);
        beneficiary.setComment(comment);
        beneficiary=beneficiaryRepository.save(beneficiary);
        return new ResponseEntity<>(beneficiary.getLost().getName()+" "+beneficiary.getReunification()+" Action saved succesful",HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>("Beneficiary doesn't exist",HttpStatus.BAD_REQUEST);
       }
}
public ResponseEntity<String> assignBeneficiaryOrganization(UUID beneficiaryId, Reunify reunify, long organizationId) {
    try {
        Beneficiary beneficiary=beneficiaryRepository.findById(beneficiaryId).orElseThrow();
        beneficiary.setReunification(reunify);
        Organization organization=organizationRepository.findById(organizationId).orElseThrow();
        beneficiary.setOrganization(organization);
        beneficiary=beneficiaryRepository.save(beneficiary);
        return new ResponseEntity<>(beneficiary.getLost().getName()+" "+beneficiary.getReunification()+" Action saved succesful",HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>("Beneficiary doesn't exist",HttpStatus.BAD_REQUEST);
       }
}
}
