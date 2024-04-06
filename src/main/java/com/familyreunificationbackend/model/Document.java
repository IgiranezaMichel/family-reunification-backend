package com.familyreunificationbackend.model;

import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
@ManyToOne(cascade = CascadeType.ALL,targetEntity=Lost.class)
@OnDelete(action = OnDeleteAction.CASCADE)
private Lost lostDocument;
}
