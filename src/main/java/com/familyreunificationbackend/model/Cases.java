package com.familyreunificationbackend.model;
import com.familyreunificationbackend.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cases {
@Id()
@GeneratedValue(strategy = GenerationType.AUTO) 
private long id;
private String title;
private String description;
@ManyToOne(cascade = CascadeType.ALL,targetEntity =User.class)
private User user;
private Role role;
}
