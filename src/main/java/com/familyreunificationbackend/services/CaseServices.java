package com.familyreunificationbackend.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.time.*;

import org.hibernate.bytecode.enhance.spi.EnhancementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.dto.ChartDTO;
import com.familyreunificationbackend.model.Cases;
import com.familyreunificationbackend.repository.CasesRepository;
import com.familyreunificationbackend.repository.LostRepository;

@Service
public class CaseServices {
    @Autowired
    private CasesRepository caseRepository;
    @Autowired
    private LostRepository lostRepository;

    public ResponseEntity<String> saveOrUpdate(Cases cases) {
        try {
            cases.setTimeStamp(LocalDateTime.now());
            boolean isFound = caseRepository.existsByTitle(cases.getTitle());
            if (isFound)
                throw new Exception("Case already exist");
            Cases case1 = caseRepository.save(cases);
            return new ResponseEntity<String>(case1.getTitle() + " created successful", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found", HttpStatus.METHOD_NOT_ALLOWED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public Cases findCaseById(long id) {
        return caseRepository.findById(id).orElseThrow(() -> new EnhancementException("Please select case"));
    }

    public ResponseEntity<String> deleteCase(long id) {
        try {
            Cases cases = this.findCaseById(id);
            caseRepository.delete(cases);
            return new ResponseEntity<String>(cases.getTitle() + " deleted successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(" case not found", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public List<Cases> caseList() {
        return caseRepository.findAll();
    }

    public long totalCases() {
        return caseRepository.count();
    }

    public long totalResolvedCases() {
        return lostRepository.countByHasFound(true);
    }

    public long totalPendingCases() {
        return lostRepository.countByHasFound(false);
    }

    public List<ChartDTO> getNumberOfCasesPerCaseCategory() {
        return caseRepository.getNumberOfCasesPerCaseCategory();
    }
}
