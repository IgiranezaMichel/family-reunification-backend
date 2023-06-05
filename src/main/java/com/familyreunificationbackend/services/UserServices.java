package com.familyreunificationbackend.services;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.enums.Role;
import com.familyreunificationbackend.input.UserInput;
import com.familyreunificationbackend.model.User;
import com.familyreunificationbackend.model.pagination.UserPage;
import com.familyreunificationbackend.model.paginationDefinition.PaginationInput;
import com.familyreunificationbackend.repository.UserRepository;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> saveOrUpdateUser(UserInput userInput) {
        String img = userInput.getBase64ProfilePicture().replaceAll("data:image/png;base64,", "");
        byte[] arr = Base64.getDecoder().decode(img);
        User user = new User(userInput.getId(), userInput.getFirstName(), userInput.getLastName(), arr,
                userInput.getGender(), userInput.getEmail(), userInput.getPhoneNumber(), userInput.getDob(),
                userInput.getAddress(), userInput.getCountry(), userInput.getNativeCountry(), Role.USER,
                userInput.getUsername(), userInput.getPassword());
        User result = userRepository.save(user);
        if (userInput.getId() != 0)
            return new ResponseEntity<>("Hi " + result.getFirstName() + " Your Information has updated successful",
                    HttpStatus.OK);
        return new ResponseEntity<>("Hi " + result.getFirstName() + " Your Information has saved successful",
                HttpStatus.OK);
    }

    public User findUserById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @SuppressWarnings("null")
    public ResponseEntity<String> deleteUser(long id) {
        try {
            User user = this.findUserById(id);
            userRepository.delete(user);
            return new ResponseEntity<>(user.getFirstName() + " has deleted successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User not found", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public UserPage userPage(PaginationInput page) {
        Page<User> pagination = userRepository
                .findAll(PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by(page.getSort())));
        return new UserPage(pagination.getNumber(), page.getPageSize(), pagination.getTotalElements(),
                pagination.getContent());
    }
}
    