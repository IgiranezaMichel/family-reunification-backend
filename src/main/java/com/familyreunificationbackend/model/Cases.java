package com.familyreunificationbackend.model;
import java.time.format.DateTimeFormatter;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
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
private LocalDateTime timeStamp;
@OneToMany(cascade =CascadeType.ALL,targetEntity =Lost.class)
private List<Lost>lostCases;
public String getTimeStamp(){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss a");
    return formatter.format(timeStamp);
}
}
