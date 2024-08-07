package com.familyreunificationbackend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import java.util.List;
import com.familyreunificationbackend.dto.LostDTO;
import com.familyreunificationbackend.dto.LostPageDTO;
import com.familyreunificationbackend.dto.LostPageInput;
import com.familyreunificationbackend.input.PaginationInput;
import com.familyreunificationbackend.model.Lost;
import com.familyreunificationbackend.services.LostServices;
@Controller
public class LostController {
    @Autowired
    private LostServices lostServices;

    @MutationMapping
    public ResponseEntity<String> saveLost(@Argument(name = "lost") LostDTO lostDTO) {
        return lostServices.saveLost(lostDTO);
    }
    @MutationMapping
    public ResponseEntity<String> claimFound(@Argument(name = "lostId") UUID lostId,@Argument(name = "hasFound")boolean hasFound) {
        return lostServices.claimFound(lostId,hasFound);
    }
    @MutationMapping
    public ResponseEntity<String> deleteLost(@Argument(name = "id") UUID lostId) {
        return lostServices.deleteLost(lostId);
    }

    @QueryMapping
    public List<Lost> lostList() {
        return lostServices.findAll();
    }

    @QueryMapping
    public List<Lost> customerLostPosts(@Argument(name = "customerId") long customerId,@Argument(name = "hasFound")boolean hasFound) {
        return lostServices.getCustomerLostPosts(customerId,hasFound);
    }

    @QueryMapping
    public Lost findLostById(@Argument(name = "customerId") UUID customerId) {
        return lostServices.findById(customerId);
    }

    // admin
    @QueryMapping
    public LostPageDTO<Lost> lostPageable(@Argument(name = "input") LostPageInput lostPageInput) {
        return lostServices.lostPageable(lostPageInput);
    }

    @QueryMapping
    public LostPageDTO<Lost> lostPageableIndex(@Argument(name = "pageInput") PaginationInput page,
            @Argument(name = "hasFound") boolean hasFound, @Argument(name = "location") String location) {
        return lostServices.lostPageableDefault(page, hasFound, location);
    }

    // customer
    @QueryMapping
    public List<Lost> customerLostFounds(@Argument(name = "username") String username,
            @Argument(name = "sort") String sort, @Argument(name = "hasFound") boolean hasFound) {
        return lostServices.customerLostFoundList(username, sort, hasFound);
    }
    @QueryMapping
    public List<Lost> searchLost(@Argument(name = "search") String search) {
        String searchingString = search.toLowerCase();
        return lostServices.searchLost(searchingString);
    }
    @QueryMapping
    public long totalCustomerPostByHasFound(@Argument(name = "customerUsername")String customerUsername,@Argument(name = "hasFound")boolean hasFound){
        return lostServices.totalCustomerPostByHasFound(customerUsername, hasFound);
    }
    @QueryMapping
    public long totalLost(){return lostServices.totalLost();}
    @QueryMapping
    public List<Lost> lostReportList(@Argument(name = "getAll") boolean getAll,@Argument(name = "hasFound")boolean hasFound) {
        return lostServices.lostReportList(getAll,hasFound);
    }
    
}