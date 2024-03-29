package com.familyreunificationbackend.model.paginationDefinition;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class PaginationContent<T> {
private int pageNumber;
private int pageSize;
private long size;
private List<T> content;
}
