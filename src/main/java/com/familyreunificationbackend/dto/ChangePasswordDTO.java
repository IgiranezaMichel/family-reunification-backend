package com.familyreunificationbackend.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {
   private String username;
   private String oldPassword;
   private String newPassword;
   private String retypePassword;
    }
