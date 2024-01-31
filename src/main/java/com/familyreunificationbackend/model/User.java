package com.familyreunificationbackend.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
private long id;
private String firstName;
private String lastName;
private String gender;
private String email;
private LocalDate dob;
private String address;
private String username;
private String password;
}
