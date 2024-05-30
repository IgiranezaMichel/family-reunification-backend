package com.familyreunificationbackend.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.familyreunificationbackend.model.Customer;
import com.familyreunificationbackend.model.Lost;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
public interface LostRepository extends JpaRepository<Lost,UUID>{

    List<Lost> findAllByPostedBy(Customer customer);
    Object findAllByHasFoundAndCountryOfLostAndTimeStamp(boolean hasFound, String country, LocalDate fromDate,
            PageRequest page);
    @Query("Select l from Lost l where l.hasFound=:hasFound and CAST(l.timeStamp AS DATE)>=:fromDate and cast(l.timeStamp AS DATE)<=:toDate or l.countryOfLost=:countryOfLost")
    Page<Lost> findAllByLostFilter(boolean hasFound, String countryOfLost,LocalDate fromDate,LocalDate toDate, PageRequest page);
    List<Lost> findAllByPostedByAndHasFound(Customer customer, boolean hasFound, Sort by);

}
