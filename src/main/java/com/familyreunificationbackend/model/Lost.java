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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Lost {
@Id @UuidGenerator(style = Style.AUTO)
private UUID id;
private String firstName;
private String lastName;
private String gender;
private String address;
private String phoneNumber;
private String country;
private String nativeCountry;
private LocalDate dob;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Cases.class)
private Cases cases;
@Column(columnDefinition = "text")
private String description;
@OneToMany(cascade=CascadeType.ALL, targetEntity=Document.class)
private List<Document> document;
}
