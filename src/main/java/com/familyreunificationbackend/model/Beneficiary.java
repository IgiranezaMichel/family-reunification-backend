package com.familyreunificationbackend.model;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.familyreunificationbackend.enums.Reunify;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Beneficiary {
@UuidGenerator(style = Style.AUTO)
@Id
private UUID id;
@Enumerated(EnumType.STRING)
private Reunify reunification;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Lost.class)
private Lost lost;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Organization.class)
private Organization organization;
private String description;
private String countryFound;
private String adress;
private String countryOfDestination;
private String destinationAddress;
private String comment;
}
