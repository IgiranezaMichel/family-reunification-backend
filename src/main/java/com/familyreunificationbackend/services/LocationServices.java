package com.familyreunificationbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.repository.LocationRepository;

@Service
public class LocationServices {
@Autowired private LocationRepository locationRepository;
}
