package com.familyreunificationbackend.model.pagination;

import java.util.List;

import com.familyreunificationbackend.model.Customer;
import com.familyreunificationbackend.model.paginationDefinition.PaginationContent;

public class UserPage extends PaginationContent<Customer>{

    public UserPage(int pageNumber, int pageSize, long size, List<Customer> content) {
        super(pageNumber, pageSize, size, content);
    }

}
