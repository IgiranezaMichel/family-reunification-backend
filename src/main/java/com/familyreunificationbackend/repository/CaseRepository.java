package com.familyreunificationbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.familyreunificationbackend.model.Case;

public interface CaseRepository extends JpaRepository<Case,Long>{

}
