package com.familyreunificationbackend.controller.restapi;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.familyreunificationbackend.dto.ChatListDTO;
import com.familyreunificationbackend.dto.ChatRoomDTO;
import com.familyreunificationbackend.model.ChatRoom;
import com.familyreunificationbackend.restbodyinput.ChatRoomRBI;
import com.familyreunificationbackend.services.ChatRoomServices;
import com.familyreunificationbackend.services.CustomerServices;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/api/chatroom")
@Slf4j
public class ChatRoomRestController {
    @Autowired private ChatRoomServices chatRoomServices;
    @Autowired private CustomerServices customerServices;
@PostMapping(value = "/create")
public ResponseEntity<ChatRoomDTO> ChatMessage(@RequestBody ChatRoomRBI chatRoomDTO) {
    ChatRoom chatRoom= chatRoomServices.createChatRoom(chatRoomDTO);
    return new ResponseEntity<>(new ChatRoomDTO(chatRoom.getId(), chatRoom.getChatInitiator().getUsername(),chatRoom.getChatInitiator().getFirstName()+" "+chatRoom.getChatInitiator().getLastName(),chatRoom.getChatInitiator().getProfilePicture(), chatRoom.getChatSubscriber().getUsername(),chatRoom.getChatSubscriber().getProfilePicture(),chatRoom.getChatSubscriber().getFirstName()+" "+chatRoom.getChatSubscriber().getLastName()),HttpStatus.OK);
}
@GetMapping("/all")
public List<ChatRoomDTO> getAllChatRooms(Principal principal,@RequestParam String search) {
    log.info("Username ============ {}",principal.getName());
       return chatRoomServices.getAllUserChatRooms(principal.getName(),search);
}
@GetMapping("user/all")
public List<ChatListDTO>getAllCustomer(){
    return customerServices.getAllCutomerToChatList();
}
}
