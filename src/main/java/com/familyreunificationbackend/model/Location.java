package com.familyreunificationbackend.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location {
@Id @GeneratedValue(strategy = GenerationType.AUTO)
private long id;
private String name;
private String type;
@ManyToOne()
private Location location;
@OneToMany(mappedBy = "location")
private List<Location>locationList;
}
