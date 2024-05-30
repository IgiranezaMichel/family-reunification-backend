package com.familyreunificationbackend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor @NoArgsConstructor @Data
public class LostPageDTO <T>{
private long pageNumber;
private long totalPage;
private long total;
private List<T> content;
}
