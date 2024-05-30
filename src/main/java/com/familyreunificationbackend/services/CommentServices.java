package com.familyreunificationbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.familyreunificationbackend.dto.CommentInput;
import com.familyreunificationbackend.model.Comment;
import com.familyreunificationbackend.model.Customer;
import com.familyreunificationbackend.model.Lost;
import com.familyreunificationbackend.repository.CommentRepository;
import java.util.UUID;

@Service
public class CommentServices {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private LostServices lostServices;
    @Autowired
    private CustomerServices customerServices;

    public ResponseEntity<String> save(CommentInput comment) {
        try {
            Lost lost = lostServices.findById(comment.getLostId());
        Customer customer = customerServices.findCustomerById(comment.getCustomerId());
        Comment comment2 = commentRepository.save(new Comment(comment.getId(), comment.getComment(), lost, customer));
            return new ResponseEntity<>(comment2.getCustomer().getLastName() + " saved successfully",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public Lost findById(UUID id) throws Exception {
        return lostServices.findById(id);
    }

    public ResponseEntity<String> delete(UUID id) {
        try {
            lostServices.deleteLost(id);
            return new ResponseEntity<>("comment removed successful", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        }

    }
}
