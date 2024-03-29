package com.familyreunificationbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.familyreunificationbackend.model.UserCase;

public interface CaseRepository extends JpaRepository<UserCase,Long>{

}
