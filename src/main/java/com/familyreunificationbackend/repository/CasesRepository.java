package com.familyreunificationbackend.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.familyreunificationbackend.dto.ChartDTO;
import com.familyreunificationbackend.model.Cases;
public interface CasesRepository extends JpaRepository<Cases,Long>{

    boolean existsByTitle(String title);

    @Query("SELECT new com.familyreunificationbackend.dto.ChartDTO(c.title,COUNT(a.cases)) FROM Cases c,Lost a   where c.id=a.cases.id  GROUP BY a.cases,c.id")
    List<ChartDTO>getNumberOfCasesPerCaseCategory();
}
