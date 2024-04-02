package com.familyreunificationbackend.model;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import com.familyreunificationbackend.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
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
    public User(long id,String firstName,String lastName,byte[] profilePicture, String gender,
   String email, String phoneNumber, LocalDate dob, String address, String country, String nativeCountry
    ,Role role, String username, String password){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.profilePicture=profilePicture;
        this.gender=gender;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.dob=dob;
        this.address=address;
        this.country=country;
        this.nativeCountry=nativeCountry;
        this.role=role;
        this.username=username;
        this.password=password;
    }
    @OneToMany(mappedBy = "user",targetEntity=Cases.class,cascade =CascadeType.ALL )
    public List<Cases>caseList;
}
