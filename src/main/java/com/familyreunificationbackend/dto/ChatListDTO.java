package com.familyreunificationbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatListDTO { 
private String firstName;
    private String lastName;
    private String profilePicture;
    private String username;
    private String role;
}
