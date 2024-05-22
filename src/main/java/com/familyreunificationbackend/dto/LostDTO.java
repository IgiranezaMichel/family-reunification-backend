package com.familyreunificationbackend.dto;

import com.familyreunificationbackend.model.Lost;

import lombok.Getter;
import lombok.Setter;

public class LostDTO extends Lost {
    @Getter
    @Setter
    private long caseId;
    @Getter
    @Setter
    private long customerId;
    @Getter
    @Setter
    private String  base64Profile;
}
