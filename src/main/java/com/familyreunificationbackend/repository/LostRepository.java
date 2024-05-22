package com.familyreunificationbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.familyreunificationbackend.model.Lost;
import java.util.UUID;
public interface LostRepository extends JpaRepository<Lost,UUID>{

}
