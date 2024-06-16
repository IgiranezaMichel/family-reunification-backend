package com.familyreunificationbackend.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@AllArgsConstructor
public class ChatRoomDTO {
private UUID id;
private String initiatorUsername;
private String initiatorName;
private String initiatorProfile;
private String chatSubscriberUsername;
private String chatSubscriberProfile;
private String chatSubscriberName;
}
