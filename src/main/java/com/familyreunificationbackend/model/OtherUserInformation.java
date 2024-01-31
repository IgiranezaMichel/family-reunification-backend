package com.familyreunificationbackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OtherUserInformation {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
private long id;
private String fatherName;
private String motherName;

}
