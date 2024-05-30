package com.familyreunificationbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.familyreunificationbackend.model.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment,UUID>{

}
