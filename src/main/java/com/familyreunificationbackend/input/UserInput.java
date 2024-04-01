package com.familyreunificationbackend.input;

import com.familyreunificationbackend.model.User;

import lombok.Getter;
import lombok.Setter;
public class UserInput extends User{
    @Getter @Setter
    private String base64ProfilePicture;

}
