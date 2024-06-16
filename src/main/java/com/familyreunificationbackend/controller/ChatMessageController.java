package com.familyreunificationbackend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.familyreunificationbackend.dto.ChatMessageDTO;
import com.familyreunificationbackend.services.ChatMessageService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ChatMessageController {
@Autowired private SimpMessageSendingOperations  simpMessagingTemplate;
@Autowired private ChatMessageService chatMessageService;
@MessageMapping("/send-message")
public void sendMessage(@Payload ChatMessageDTO chatMessage){
    ChatMessageDTO chatMessageResult = chatMessageService.createChatMessage(chatMessage);
    String chatRoom=chatMessageResult.getChatRoomId().toString();
    simpMessagingTemplate.convertAndSendToUser(chatRoom, "/queue/massages", chatMessageResult);
}
@GetMapping(value = "/chat/{chat-room}")
public ResponseEntity<List<ChatMessageDTO>>getAllChatMessageByChatRoom(@PathVariable(name = "chat-room")UUID chatRoom){
    log.info("chat room messages {}",chatMessageService.getAllChatRoomMessages(chatRoom));
return new ResponseEntity<>(chatMessageService.getAllChatRoomMessages(chatRoom),HttpStatus.OK);
}
}
