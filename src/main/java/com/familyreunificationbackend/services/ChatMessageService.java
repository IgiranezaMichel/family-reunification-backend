package com.familyreunificationbackend.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.familyreunificationbackend.dto.ChatMessageDTO;
import com.familyreunificationbackend.model.ChatMessage;
import com.familyreunificationbackend.model.ChatRoom;
import com.familyreunificationbackend.model.Customer;
import com.familyreunificationbackend.repository.ChatMessageRepository;

@Service
public class ChatMessageService {
        @Autowired
        private ChatMessageRepository chatMessageRepository;
        @Autowired
        private ChatRoomServices chatRoomServices;
        @Autowired
        private CustomerServices customerServices;
        public ChatMessageDTO createChatMessage(ChatMessageDTO chatMessageDTO) {
                Customer sender = customerServices.findByUsername(chatMessageDTO.getSenderUsername());
                Customer receiver = customerServices.findByUsername(chatMessageDTO.getReceiverUsername());
                ChatRoom chatRoom = chatRoomServices.chatRoomExist(sender, receiver);
                if (chatRoom == null) {
                        chatRoom = chatRoomServices.createNewChatRoom(sender, receiver);
                        ChatMessage chatMessage = chatMessageRepository
                                        .save(new ChatMessage(null, chatMessageDTO.getMessage(), sender, receiver,
                                                        new Date(), chatRoom));
                        return new ChatMessageDTO(chatMessage.getId(), chatMessage.getMessage(),
                                        chatMessage.getSender().getUsername(), chatMessage.getReceiver().getUsername(),
                                        chatMessage.getTimestamp(), chatMessage.getChatRoom().getId());
                } else {
                        ChatMessage chatMessage = chatMessageRepository
                                        .save(new ChatMessage(null, chatMessageDTO.getMessage(), sender, receiver,
                                                        new Date(), chatRoom));
                        return new ChatMessageDTO(chatMessage.getId(), chatMessage.getMessage(),
                                        chatMessage.getSender().getUsername(), chatMessage.getReceiver().getUsername(),
                                        chatMessage.getTimestamp(), chatMessage.getChatRoom().getId());
                }
        }

        public List<ChatMessageDTO> getAllChatRoomMessages(UUID chatRoomId) {
                ChatRoom chatRoom = chatRoomServices.findChatRoomById(chatRoomId);
                return chatMessageRepository.findAllByChatRoom(chatRoom, Sort.by("timestamp").ascending()).stream()
                                .map(data -> new ChatMessageDTO(data.getId(), data.getMessage(),
                                                data.getSender().getUsername(),
                                                data.getReceiver().getUsername(), data.getTimestamp(), chatRoomId))
                                .toList();
        }
}
