package com.familyreunificationbackend.input;

import com.familyreunificationbackend.model.Organization;

import lombok.Getter;
import lombok.Setter;

public class OrganizationInput extends Organization{
@Getter @Setter
    private String base64Logo;
}
