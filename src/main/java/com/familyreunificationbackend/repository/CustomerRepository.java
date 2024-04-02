package com.familyreunificationbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.familyreunificationbackend.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
