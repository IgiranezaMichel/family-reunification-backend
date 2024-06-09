package com.familyreunificationbackend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;
import com.familyreunificationbackend.model.ChatMessage;
import com.familyreunificationbackend.model.ChatRoom;
import com.familyreunificationbackend.model.Customer;
@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage,UUID>{
    List<ChatMessage> findAllBySenderOrReceiver(Customer customer, Customer customer2);

    List<ChatMessage> findAllByChatRoom(ChatRoom chatRoom);

    Streamable<ChatMessage> findAllByChatRoom(ChatRoom chatRoom, Sort ascending);
}
