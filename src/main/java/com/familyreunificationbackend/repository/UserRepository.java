package com.familyreunificationbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.familyreunificationbackend.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
