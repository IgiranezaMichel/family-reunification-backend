package com.familyreunificationbackend.input;

import com.familyreunificationbackend.model.Customer;

import lombok.Getter;
import lombok.Setter;
public class CustomerInput extends Customer{
    @Getter @Setter
    private String base64ProfilePicture;

}
