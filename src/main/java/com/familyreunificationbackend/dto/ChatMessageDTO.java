package com.familyreunificationbackend.dto;
import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {
private UUID id;
private String message;
private String senderUsername;
private String receiverUsername;
private Date timestamp;
private UUID chatRoomId;
}
