package com.familyreunificationbackend.model;

import java.time.LocalDate;
import java.util.Base64;

import com.familyreunificationbackend.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    @Lob
    @Column(columnDefinition = "longBlob")
    private byte[] profilePicture;
    @Column(length = 10)
    private String gender;
    private String email;
    @Column(length = 15)
    private String phoneNumber;
    private LocalDate dob;
    private String address;
    private String country;
    private String nativeCountry;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String username;
    private String password;
    public String getProfilePicture(){
        return Base64.getEncoder().encodeToString(profilePicture);
    }
}
