package com.familyreunificationbackend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
 @Id   @UuidGenerator(style = Style.AUTO)
private UUID id;
private String comment;
@ManyToOne(cascade = CascadeType.ALL,targetEntity =Lost.class)
private Lost lost;
@ManyToOne(cascade = CascadeType.ALL,targetEntity =Customer.class)
private Customer customer;
}
