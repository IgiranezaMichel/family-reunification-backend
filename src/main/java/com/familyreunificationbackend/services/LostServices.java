package com.familyreunificationbackend.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import org.hibernate.bytecode.enhance.spi.EnhancementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.familyreunificationbackend.dto.LostDTO;
import com.familyreunificationbackend.dto.LostPageDTO;
import com.familyreunificationbackend.dto.LostPageInput;
import com.familyreunificationbackend.input.PaginationInput;
import com.familyreunificationbackend.model.Cases;
import com.familyreunificationbackend.model.Customer;
import com.familyreunificationbackend.model.Lost;
import com.familyreunificationbackend.repository.LostRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
            Lost lost= lostRepository
                    .save(new Lost(lostDTO.getName(), lostDTO.getGender(), lostDTO.getAddress(),
                            lostDTO.getPhoneNumber(), lostDTO.getCurrentCountry(), lostDTO.getNativeCountry(), arr,
                            lostDTO.getDob(), cases, lostDTO.getCountryOfLost(), lostDTO.getExpectedAddress(),
                            lostDTO.getRelationShip(), false, customer,LocalDateTime.now(), lostDTO.getDescription()));
            return new ResponseEntity<>("Hi ,"+lost.getName() + " claim has added successful", HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            log.info("save lost {}",e);
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(404));
        }
    }

    public Lost findById(UUID id){
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
    public List<Lost>getCustomerLostPosts(long customerId){
        Customer customer=customerServices.findCustomerById(customerId);
        return lostRepository.findAllByPostedBy(customer);
    }
    public LostPageDTO<Lost> lostPageable(LostPageInput input){
        var page=PageRequest.of(input.getPageNumber(), input.getPageSize(), Sort.by(input.getSort()));
        if(input.getToDate()==null)input.setToDate(LocalDate.now());
        if(input.getFromDate()==null)input.setFromDate(LocalDate.now());
        if(input.getFromDate().isAfter(LocalDate.now()))input.setFromDate(LocalDate.now());
        if(input.getFromDate().isAfter(input.getToDate()))input.setToDate(LocalDate.now());
        var paginationResult=lostRepository.findAllByLostFilter(input.getHasFound(),input.getCountry(),input.getFromDate(),input.getToDate(),page);
        return new  LostPageDTO<>(paginationResult.getNumber(), paginationResult.getTotalPages(), paginationResult.getTotalElements(), paginationResult.getContent());
    }

    public List<Lost> customerLostFoundList(String username, String sort,boolean hasFound) {
        Customer customer=customerServices.findByUsername(username);
        return lostRepository.findAllByPostedByAndHasFound(customer,hasFound,Sort.by(sort));
    }

    public LostPageDTO<Lost> lostPageableDefault(PaginationInput page, boolean hasFound, String location) {
        Page<Lost> lostPage=null;
        if(location!=null) {lostPage= lostRepository.findAllByHasFoundAndNativeCountry(hasFound,location,PageRequest.of(page.getPageNumber(), page.getPageSize(),Sort.by(page.getSort())));}
        else{lostPage= lostRepository.findAllByHasFound(hasFound,PageRequest.of(page.getPageNumber(), page.getPageSize(),Sort.by(page.getSort())));}
        return new  LostPageDTO<>(lostPage.getNumber(), lostPage.getTotalPages(), lostPage.getTotalElements(), lostPage.getContent());
    }
    public List<Lost>searchLost(String search){
        if(search.equals(null)||search.equals(""))
        return lostRepository.findAllByHasFound(false);
        return lostRepository.findAllByNameContainingAndHasFound(search,false);
    }
    public long totalLost(){
        return lostRepository.countByHasFound(false);
    }
    public long totalCustomerPostByHasFound(String customerUsername,boolean hasFound){
        Customer customer=customerServices.findByUsername(customerUsername);
        return lostRepository.countByPostedByAndHasFound(customer,hasFound);
    }
    public List<Lost>lostReportList(boolean getAll,boolean hasFound){
        if(getAll){
            return lostRepository.findAll();
        }
        return lostRepository.findAllByHasFound(hasFound);
    }

    public ResponseEntity<String> claimFound(UUID lostId, boolean hasFound) {
        Lost lost=this.findById(lostId);
        lost.setHasFound(hasFound);
        lost.setTimeStamp(LocalDateTime.now());
        Lost lost1=lostRepository.save(lost);
        return new ResponseEntity<>(lost1.getName()+" status has saved successful",HttpStatus.OK);
    }

    public List<Lost> getCustomerLostPosts(long customerId, boolean hasFound) {
        Customer customer=customerServices.findCustomerById(customerId);
     return lostRepository.findAllByPostedByAndHasFound(customer,hasFound);
    }
}
