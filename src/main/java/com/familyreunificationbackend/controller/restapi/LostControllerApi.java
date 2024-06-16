package com.familyreunificationbackend.controller.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.familyreunificationbackend.model.Lost;
import com.familyreunificationbackend.services.LostServices;

import reactor.core.publisher.Flux;

@RestController
public class LostControllerApi {
    @Autowired
    private LostServices lostServices;

    @GetMapping(value = "/search")
    public Flux<Lost> searchLost(@RequestParam String search) {
        String searchingString = search.toLowerCase();
        List<Lost>losts= lostServices.searchLost(searchingString);
        return Flux.fromIterable(losts);
    }
}
