package com.familyreunificationbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.familyreunificationbackend.model.Cases;
public interface CasesRepository extends JpaRepository<Cases,Long>{

    boolean existsByTitle(String title);

}
