package com.familyreunificationbackend.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.familyreunificationbackend.dto.ChatRoomDTO;
import com.familyreunificationbackend.model.ChatMessage;
import com.familyreunificationbackend.model.ChatRoom;
import com.familyreunificationbackend.restbodyinput.ChatRoomRBI;
import com.familyreunificationbackend.services.ChatRoomServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class ChatRoomController {
    @Autowired
    private ChatRoomServices chatRoomServices;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, Principal principal) {
        log.info("chat message {}", chatMessage);
        log.info("Principal {}", principal.getName());
        return chatMessage;
    }

    @GetMapping ("/chat/find-chatroom")
    public ResponseEntity<ChatRoomDTO> checkChatRoomExistence(@RequestParam String receiverId, Principal principal) {
        ChatRoom chatRoom = chatRoomServices.createChatRoom(new ChatRoomRBI(null, principal.getName(), receiverId));
        ChatRoomDTO chatRoomDTO = new ChatRoomDTO(chatRoom.getId(), chatRoom.getChatInitiator().getUsername(),
                chatRoom.getChatInitiator().getFirstName() + " " + chatRoom.getChatInitiator().getLastName(),
                chatRoom.getChatInitiator().getProfilePicture(), chatRoom.getChatSubscriber().getUsername(),
                chatRoom.getChatSubscriber().getProfilePicture(),
                chatRoom.getChatSubscriber().getFirstName() + " " + chatRoom.getChatSubscriber().getLastName());
        return new ResponseEntity<>(chatRoomDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/chat/chatroom")
    public ResponseEntity<List<ChatRoomDTO>> chatRoomList(Principal principal) {
        return new ResponseEntity<>(chatRoomServices.getAllUserChatRooms(principal.getName()), HttpStatus.OK);
    }
}
