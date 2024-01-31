package com.familyreunificationbackend.model;
import java.time.LocalDateTime;
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
public class LostPersonDescriptionDetails {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
private long id;
private LocalDateTime lastSeen;
private String description;
private boolean isFound;
private String addressOfExpectedLost;
}
