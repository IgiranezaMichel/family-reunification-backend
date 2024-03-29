package com.familyreunificationbackend.model.pagination;

import java.util.List;

import com.familyreunificationbackend.model.Organization;
import com.familyreunificationbackend.model.paginationDefinition.PaginationContent;

public class OrganizationPage extends PaginationContent<Organization> {
    public OrganizationPage(int pageNumber, int pageSize, long size, List<Organization> content) {
        super(pageNumber, pageSize, size, content);
    }

}
