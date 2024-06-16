package com.familyreunificationbackend.model;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
@Id
@UuidGenerator(style = Style.AUTO)
private UUID id;
@Column(columnDefinition = "text")
private String message;
@ManyToOne(cascade = CascadeType.MERGE,targetEntity = Customer.class)
private Customer sender;
@ManyToOne(cascade = CascadeType.MERGE,targetEntity = Customer.class)
private Customer receiver;
private Date timestamp;
@ManyToOne(cascade = CascadeType.MERGE,targetEntity = ChatRoom.class)
private ChatRoom chatRoom;
}
