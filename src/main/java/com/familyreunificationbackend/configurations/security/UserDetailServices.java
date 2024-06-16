package com.familyreunificationbackend.configurations.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.model.Customer;
import com.familyreunificationbackend.services.CustomerServices;

@Service
public class UserDetailServices implements UserDetailsService {
    @Autowired private CustomerServices customerServices;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer accountHolder = customerServices.findByUsername(username);
        if (accountHolder == null)  
            throw new UsernameNotFoundException("Unimplemented method  loadUserByUsername");
        return new UserDetailPrinciple(accountHolder);
    }
}