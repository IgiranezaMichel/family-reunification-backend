package com.familyreunificationbackend.model;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {
@UuidGenerator(style =Style.AUTO )
@Id
private UUID id;
@ManyToOne(cascade = CascadeType.MERGE,targetEntity = Customer.class)
private Customer chatInitiator;
@ManyToOne(cascade = CascadeType.MERGE,targetEntity = Customer.class)
private Customer chatSubscriber;
@OneToMany(mappedBy="chatRoom",targetEntity=ChatMessage.class,cascade=CascadeType.ALL)
private List<ChatMessage>chatMessages;
public ChatRoom(Customer chatInitiator, Customer chatSubscriber) {
    this.chatInitiator = chatInitiator;
    this.chatSubscriber = chatSubscriber;
}
public ChatRoom(UUID id, Customer chatInitiator, Customer chatSubscriber) {
    this.id = id;
    this.chatInitiator = chatInitiator;
    this.chatSubscriber = chatSubscriber;
}
}
