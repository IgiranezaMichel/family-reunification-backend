package com.familyreunificationbackend.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class LostPageInput {
    private String country;
    private LocalDate fromDate;
    private  LocalDate toDate;
    private int pageNumber;
    private int pageSize;
    private String sort;
    private boolean hasFound;
    public boolean getHasFound(){
      return hasFound;
    }
  }
