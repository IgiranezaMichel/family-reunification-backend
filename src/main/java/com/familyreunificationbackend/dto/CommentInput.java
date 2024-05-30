package com.familyreunificationbackend.dto;

import com.familyreunificationbackend.model.Comment;

import lombok.Getter;
import lombok.Setter;
import java.util.*;
public class CommentInput extends Comment {
    @Getter
    @Setter
    private UUID lostId;
    @Getter
    @Setter
    private long customerId;
}
