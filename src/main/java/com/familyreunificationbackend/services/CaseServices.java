package com.familyreunificationbackend.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.model.Cases;
import com.familyreunificationbackend.model.User;
import com.familyreunificationbackend.repository.CasesRepository;

@Service
public class CaseServices {
    @Autowired private CasesRepository caseRepository;
    @Autowired private UserServices userServices;
    public ResponseEntity<String> saveOrUpdate(Cases cases,long userId){
        try {
        User user=userServices.findUserById(userId);
        cases.setUser(user);
        boolean isFound=caseRepository.existsByTitle(cases.getTitle());
        if(isFound)throw new Exception("Case already exist");
        Cases case1=caseRepository.save(cases);
        return new ResponseEntity<String>(case1.getTitle()+" created successful",HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found",HttpStatus.METHOD_NOT_ALLOWED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    public Cases findCaseById(long id){
        return caseRepository.findById(id).orElseThrow();
    }
    @SuppressWarnings("null")
    public ResponseEntity<String> deleteCase(long id){
        try {
            Cases cases=this.findCaseById(id);
            caseRepository.delete(cases);
            return new ResponseEntity<String>(cases.getTitle()+" deleted successful",HttpStatus.OK); 
        } catch (Exception  e) {
            return new ResponseEntity<String>(" case not found",HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    public List<Cases>caseList(){
        return caseRepository.findAll();
    }
}
