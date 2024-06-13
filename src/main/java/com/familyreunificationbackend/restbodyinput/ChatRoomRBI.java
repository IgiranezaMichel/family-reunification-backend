package com.familyreunificationbackend.restbodyinput;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomRBI {
private UUID id;
private String initiatorUsername;
private String chatSubscriberUsername;
}
