package com.familyreunificationbackend.input;

import com.familyreunificationbackend.model.Document;

import lombok.Getter;
import lombok.Setter;

public class DocumentInput extends Document{
@Getter @Setter
private String base64Document;
}
