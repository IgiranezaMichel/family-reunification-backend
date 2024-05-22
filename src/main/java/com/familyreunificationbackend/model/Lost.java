package com.familyreunificationbackend.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@Entity
public class Lost {
@Id @UuidGenerator(style = Style.AUTO)
private UUID id;
private String name;
private String gender;
private String address;
private String phoneNumber;
private String currentCountry;
private String nativeCountry;
@Lob
private byte[] profile;
private LocalDate dob;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Cases.class)
private Cases cases;
private String countryOfLost;
private String expectedAddress;
private String relationShip;
private boolean hasFound;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Customer.class)
private Customer postedBy;
@Column(columnDefinition = "text")
private String description;

public Lost(UUID id, String name, String gender, String address, String phoneNumber, String currentCountry,
        String nativeCountry, byte[] profile, LocalDate dob, Cases cases, String countryOfLost, String expectedAddress,
        String relationShip, boolean hasFound, Customer postedBy, String description) {
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.currentCountry = currentCountry;
    this.nativeCountry = nativeCountry;
    this.profile = profile;
    this.dob = dob;
    this.cases = cases;
    this.countryOfLost = countryOfLost;
    this.expectedAddress = expectedAddress;
    this.relationShip = relationShip;
    this.hasFound = hasFound;
    this.postedBy = postedBy;
    this.description = description;
}
@OneToMany(cascade=CascadeType.ALL, targetEntity=Document.class)
private List<Document> document;
}
