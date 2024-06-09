package com.familyreunificationbackend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.familyreunificationbackend.enums.Reunify;
import com.familyreunificationbackend.model.Beneficiary;
import com.familyreunificationbackend.restbodyinput.BeneficiaryInput;
import com.familyreunificationbackend.services.BeneficiaryServices;
@Controller
public class BeneficiaryController {
@Autowired private BeneficiaryServices beneficiaryServices;
@MutationMapping()
public ResponseEntity<String> createBeneficiary(@Argument(name = "beneficiaryInput")BeneficiaryInput beneficiaryInput){
    return beneficiaryServices.create(beneficiaryInput);
}
@MutationMapping()
public ResponseEntity<String> updateBeneficiary(@Argument(name = "beneficiaryId")UUID beneficiaryId,@Argument(name = "reunify")Reunify reunify,@Argument(name = "comment")String comment){
    return beneficiaryServices.updateBeneficiary(beneficiaryId,reunify,comment);
}
@MutationMapping()
public ResponseEntity<String> assignBeneficiaryOrganization(@Argument(name = "beneficiaryId")UUID beneficiaryId,@Argument(name = "reunify")Reunify reunify,@Argument(name = "organizationId")long organizationId){
    return beneficiaryServices.assignBeneficiaryOrganization(beneficiaryId,reunify,organizationId);
}
// assignBeneficiaryOrganization(beneficiaryId:ID,reunify:Reunify,organizationId:Long)
@QueryMapping()
public List<Beneficiary> getBeneficiaryListByCustomer(@Argument(name ="customerUsername")String username){
    return beneficiaryServices.getCustomerBeneficiaries(username);
}
@QueryMapping()
public List<Beneficiary> getBeneficiariesByReunify(@Argument(name ="reunify")Reunify reunify){
    return beneficiaryServices.getBeneficiaryListByReunify(reunify);
}
@QueryMapping()
public List<Beneficiary>getBeneficiaryByOrganizationAndReunification(@Argument(name ="organizationId")long organizationId,@Argument(name ="reunify")Reunify reunify){
    return beneficiaryServices.getBeneficiaryOrganizationAndReunification(organizationId, reunify);
}
}
