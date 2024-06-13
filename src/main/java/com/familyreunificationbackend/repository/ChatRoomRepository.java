package com.familyreunificationbackend.repository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.familyreunificationbackend.model.ChatRoom;
import com.familyreunificationbackend.model.Customer;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID> {
    ChatRoom findByChatInitiatorAndChatSubscriber(Customer chatInitiator, Customer chatReceiver);

    ChatRoom findByChatSubscriberAndChatInitiator(Customer chatReceiver, Customer chatInitiator);

    List<ChatRoom> findAllByChatInitiatorOrChatSubscriber(Customer customer, Customer customer2);

    List<ChatRoom> findAllByChatInitiatorOrChatSubscriberOrChatInitiatorFirstNameContainingIgnoreCaseOrChatSubscriberFirstNameContainingIgnoreCase(
            Customer customer, Customer customer2, String search, String search2);
}
