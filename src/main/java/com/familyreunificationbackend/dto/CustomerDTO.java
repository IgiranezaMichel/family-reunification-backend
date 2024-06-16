package com.familyreunificationbackend.dto;

import com.familyreunificationbackend.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String gender;
    private String email;
    private String phoneNumber;
    private String address;
    private String country;
    private String nativeCountry;
    private Role role;
    private String username;
}
