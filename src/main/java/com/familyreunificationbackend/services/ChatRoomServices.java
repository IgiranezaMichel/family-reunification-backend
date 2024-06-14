package com.familyreunificationbackend.services;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.familyreunificationbackend.dto.ChatRoomDTO;
import com.familyreunificationbackend.model.ChatRoom;
import com.familyreunificationbackend.model.Customer;
import com.familyreunificationbackend.repository.ChatRoomRepository;
import com.familyreunificationbackend.restbodyinput.ChatRoomRBI;

@Service
public class ChatRoomServices {
    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private CustomerServices customerServices;

    public ChatRoom createChatRoom(ChatRoomRBI chatRoomDTO) {
        Customer customer1 = customerServices.findByUsername(chatRoomDTO.getInitiatorUsername());
        Customer customer2 = customerServices.findByUsername(chatRoomDTO.getChatSubscriberUsername());
        ChatRoom chatRoom = chatRoomExist(customer1, customer2);
        if (chatRoom == null) {
            ChatRoom createdChatRoom = this.createNewChatRoom(customer1, customer2);
            return createdChatRoom;
        } else {
            return chatRoom;
        }
    }

    public ChatRoom createNewChatRoom(Customer customer1, Customer customer2) {
        return chatRoomRepository.save(new ChatRoom(customer1, customer2));
    }

    public ChatRoom chatRoomExist(Customer chatInitiator, Customer chatReceiver) {
        ChatRoom chatRoom = chatRoomRepository.findByChatInitiatorAndChatSubscriber(chatInitiator, chatReceiver);
        if (chatRoom == null) {
            ChatRoom chatRoom1 = chatRoomRepository.findByChatSubscriberAndChatInitiator(chatInitiator,
                    chatReceiver);
            if (chatRoom1 == null)
                return null;
            else
                return chatRoom1;
        } else {
            return chatRoom;
        }
    }

    public List<ChatRoomDTO> getAllUserChatRooms(String username,String search) {
        Customer customer = customerServices.findByUsername(username);
        if(search.length()!=0){
            return chatRoomRepository.findAllByChatInitiatorOrChatSubscriber(customer, customer)
                .stream()
                .filter(i->(
                    (i.getChatInitiator().getUsername().equals(username)&&((i.getChatSubscriber().getFirstName().contains(search))||(i.getChatSubscriber().getLastName().contains(search))))
                    ||
                    (i.getChatSubscriber().getUsername().equals(username)&&((i.getChatInitiator().getFirstName().contains(search))||(i.getChatInitiator().getLastName().contains(search))))
                ))
                .map(chatRoom -> new ChatRoomDTO(chatRoom.getId(), chatRoom.getChatInitiator().getUsername(),
                        chatRoom.getChatInitiator().getFirstName() + " " + chatRoom.getChatInitiator().getLastName(),
                        chatRoom.getChatInitiator().getProfilePicture(), chatRoom.getChatSubscriber().getUsername(),
                        chatRoom.getChatSubscriber().getProfilePicture(),
                        chatRoom.getChatSubscriber().getFirstName() + " " + chatRoom.getChatSubscriber().getLastName()))
                .toList();
        }
        return chatRoomRepository.findAllByChatInitiatorOrChatSubscriber(customer, customer)
                .stream()
                .map(chatRoom -> new ChatRoomDTO(chatRoom.getId(), chatRoom.getChatInitiator().getUsername(),
                        chatRoom.getChatInitiator().getFirstName() + " " + chatRoom.getChatInitiator().getLastName(),
                        chatRoom.getChatInitiator().getProfilePicture(), chatRoom.getChatSubscriber().getUsername(),
                        chatRoom.getChatSubscriber().getProfilePicture(),
                        chatRoom.getChatSubscriber().getFirstName() + " " + chatRoom.getChatSubscriber().getLastName()))
                .toList();
    }

    public ChatRoom findChatRoomById(UUID chatRoomId) {
        return chatRoomRepository.findById(chatRoomId).orElse(null);
    }
}
