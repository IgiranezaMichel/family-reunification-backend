package com.familyreunificationbackend.services;

import java.util.Base64;
import java.util.List;

import org.hibernate.bytecode.enhance.spi.EnhancementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.familyreunificationbackend.Mapper.ChatListMapper;
import com.familyreunificationbackend.dto.ChangePasswordDTO;
import com.familyreunificationbackend.dto.ChatListDTO;
import com.familyreunificationbackend.enums.Role;
import com.familyreunificationbackend.input.CustomerInput;
import com.familyreunificationbackend.model.Customer;
import com.familyreunificationbackend.model.pagination.CustomerPage;
import com.familyreunificationbackend.model.paginationDefinition.PaginationInput;
import com.familyreunificationbackend.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServices {
    @Autowired
    private CustomerRepository userRepository;
 private ChatListMapper chatListMapper=new ChatListMapper();
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ResponseEntity<String> saveOrUpdateCustomer(CustomerInput userInput) {
        try {
            if (!userInput.getBase64ProfilePicture().contains("base64,"))
                return new ResponseEntity("Profile Picture is required",HttpStatus.BAD_REQUEST);
            String imgs = userInput.getBase64ProfilePicture().split("base64,")[1];
            byte[] arr = Base64.getDecoder().decode(imgs);
            Customer user = new Customer(userInput.getId(), userInput.getFirstName(), userInput.getLastName(), arr,
                    userInput.getGender(), userInput.getEmail(), userInput.getPhoneNumber(), userInput.getDob(),
                    userInput.getAddress(), userInput.getCountry(), userInput.getNativeCountry(),userInput.getRole(),
                    userInput.getUsername(), BCrypt.hashpw(userInput.getPassword(), BCrypt.gensalt()));
            Customer result = userRepository.save(user);
            if (userInput.getId() != 0)
                return new ResponseEntity<>("Hi " + result.getFirstName() + " Your Information has updated successful",HttpStatus.OK);
            return new ResponseEntity<>(result.getUsername()+"#" + result.getFirstName()+"Information has saved successful", HttpStatus.OK);
        } catch (Exception exp) {
            log.info("Exception {}",exp.getMessage());
            return new ResponseEntity<>("User already exist", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public Customer findCustomerById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new EnhancementException("User not found"));
    }
    public Customer findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public ResponseEntity<String> deleteCustomer(long id) {
        try {
            Customer user = this.findCustomerById(id);
            userRepository.delete(user);
            return new ResponseEntity<>(user.getFirstName() + " has deleted successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User not found", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public CustomerPage customerPage(PaginationInput page) {
        Page<Customer> pagination = userRepository
                .findAll(PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by(page.getSort())));
        return new CustomerPage(pagination.getNumber(), pagination.getTotalPages(), pagination.getTotalElements(),
                pagination.getContent());
    }

    public ResponseEntity<String> changeCustomerPassword(ChangePasswordDTO changePassword) {
        try {
            Customer customer=this.findByUsername(changePassword.getUsername());
            customer.setPassword(BCrypt.hashpw(changePassword.getNewPassword(), BCrypt.gensalt()));
            userRepository.save(customer);
            return new ResponseEntity<>("Hi "+customer.getFirstName()+" "+customer.getLastName()+" Your password changed successful",HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public ResponseEntity<String> updateCustomerRole(long id, Role role) {
       try {
         Customer customer=this.findCustomerById(id);
         customer.setRole(role);
         Customer customer2=userRepository.save(customer);
         return new ResponseEntity<>(customer2.getFirstName()+", "+"role saved successful",HttpStatus.OK);
       } catch (Exception e) {
        return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
       }
    }
    public List<ChatListDTO>getAllCutomerToChatList(){
        return userRepository.findAll().stream().map(chatListMapper).toList();
    }
}
