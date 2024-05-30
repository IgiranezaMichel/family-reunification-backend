package com.familyreunificationbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.familyreunificationbackend.dto.CommentInput;
import com.familyreunificationbackend.services.CommentServices;
import java.util.*;

@Controller
public class CommentController {
    @Autowired private CommentServices commentServices;
@MutationMapping
public ResponseEntity<String>saveComment(@Argument(name = "input")CommentInput commentDTO){
return commentServices.save(commentDTO);
}
@MutationMapping
public ResponseEntity<String>deleteComment(@Argument(name = "comment")UUID id){
return commentServices.delete(id);
}
}
