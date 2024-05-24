package com.familyreunificationbackend.services;

import java.util.Base64;
import java.util.List;
import java.util.UUID;
import org.hibernate.bytecode.enhance.spi.EnhancementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.familyreunificationbackend.dto.LostDTO;
import com.familyreunificationbackend.model.Cases;
import com.familyreunificationbackend.model.Customer;
import com.familyreunificationbackend.model.Lost;
import com.familyreunificationbackend.repository.LostRepository;

@Service
public class LostServices {
    @Autowired
    private LostRepository lostRepository;
    @Autowired
    private CaseServices caseServices;
    @Autowired
    private CustomerServices customerServices;

    public ResponseEntity<String> saveLost(LostDTO lostDTO) {
        try {
            Cases cases = caseServices.findCaseById(lostDTO.getCaseId());
            Customer customer = customerServices.findCustomerById(lostDTO.getCustomerId());
            if (!lostDTO.getBase64Profile().contains("base64,"))
                throw new Exception("Profile Picture is required");
            byte[] arr = Base64.getDecoder().decode(lostDTO.getBase64Profile().split("base64,")[1]);
            Lost lost = lostRepository
                    .save(new Lost(lostDTO.getId(), lostDTO.getName(), lostDTO.getGender(), lostDTO.getAddress(),
                            lostDTO.getPhoneNumber(), lostDTO.getCurrentCountry(), lostDTO.getNativeCountry(), arr,
                            lostDTO.getDob(), cases, lostDTO.getCountryOfLost(), lostDTO.getExpectedAddress(),
                            lostDTO.getRelationShip(), false, customer, lostDTO.getDescription()));
            return new ResponseEntity<>(lost.getName() + " ", HttpStatusCode.valueOf(0));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(404));
        }
    }

    public Lost findById(UUID id) {
        return lostRepository.findById(id).orElseThrow(() -> new EnhancementException("Lost not found"));
    }

    public ResponseEntity<String> deleteLost(UUID lostId) {
        try {
            Lost lost = this.findById(lostId);
            lostRepository.delete(lost);
            return new ResponseEntity<>(lost.getName() + " has removed successful", HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>("Lost not found", HttpStatusCode.valueOf(400));
        }
    }

    public List<Lost> findAll() {
        return lostRepository.findAll();
    }
}
