package com.familyreunificationbackend.model;
import com.familyreunificationbackend.enums.Role;
import java.time.format.DateTimeFormatter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@ManyToOne(cascade = CascadeType.ALL,targetEntity =Customer.class)
private Customer customer;
private Role role;
private LocalDateTime timeStamp;
public String getTimeStamp(){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss a");
    return formatter.format(timeStamp);
}
}
