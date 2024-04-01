package com.familyreunificationbackend.model.pagination;

import java.util.List;

import com.familyreunificationbackend.model.User;
import com.familyreunificationbackend.model.paginationDefinition.PaginationContent;

public class UserPage extends PaginationContent<User>{

    public UserPage(int pageNumber, int pageSize, long size, List<User> content) {
        super(pageNumber, pageSize, size, content);
    }

}
