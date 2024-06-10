package com.familyreunificationbackend.Mapper;

import java.util.function.Function;

import com.familyreunificationbackend.dto.ChatListDTO;
import com.familyreunificationbackend.enums.Role;
import com.familyreunificationbackend.model.Customer;

public class ChatListMapper implements Function<Customer,ChatListDTO>{

    @Override
    public ChatListDTO apply(Customer t) {
      return new ChatListDTO(t.getFirstName(), t.getLastName(),t.getProfilePicture(), t.getUsername(), t.getRole()==Role.ROLE_ADMIN?"Admin":t.getRole()==Role.ROLE_PATNER?"Partner":"");
    }

}
