package com.familyreunificationbackend.model;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Document {
@Id
@UuidGenerator(style = Style.AUTO)
private UUID id;
private String fileType;
private String fileName;
private String encodingName;
@Lob
private byte [] file;
}
