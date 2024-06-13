package com.familyreunificationbackend.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.familyreunificationbackend.enums.Reunify;
import com.familyreunificationbackend.model.Beneficiary;
import com.familyreunificationbackend.model.Customer;
import com.familyreunificationbackend.model.Lost;
import com.familyreunificationbackend.model.Organization;
@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary,UUID> {

    List<Beneficiary> findAllByReunification(Reunify reunifyStatus);
    
    List<Beneficiary> findAllByLostPostedBy(Customer cus);

    List<Beneficiary> findAllByOrganizationAndReunification(Organization organization, Reunify reunify);

    Beneficiary findByLost(Lost lost);

}
