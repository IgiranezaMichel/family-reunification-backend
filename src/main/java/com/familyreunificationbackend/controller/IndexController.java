package com.familyreunificationbackend.controller;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.familyreunificationbackend.dto.CustomerDTO;
import com.familyreunificationbackend.model.Customer;
import com.familyreunificationbackend.services.CustomerServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

@RestController
@Slf4j
public class IndexController {
    @Autowired private CustomerServices customerServices;
    @RequestMapping("/login")
    public String requestMethodName(HttpServletRequest rServletRequest, HttpServletResponse response,
            HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("servlet request {}", rServletRequest.getAuthType());
        log.info("authentication {}", auth.isAuthenticated());
        log.info("Email {}", auth.getName());
        log.info("user detail {}", auth.getDetails());
        log.info("session {}", rServletRequest.getSession().getId());
        if (!auth.getName().equals("anonymous")) {
            Cookie cookie = new Cookie("home", "null");
            response.addCookie(cookie);
        }

        return new String();
    }

    @PostMapping(value = "/success-login")
    public ResponseEntity<CustomerDTO> getMethodName(Principal principal, HttpServletRequest session) {
        Customer customer = customerServices.findByUsername(principal.getName());
        log.info("Session generated=", session.getSession().getId());
        log.info("Session generated=", session.getSession().getId());
        log.info("User name =", principal.getName());
        CustomerDTO customerDto=new CustomerDTO(customer.getId(), customer.getFirstName(), customer.getLastName(), null, customer.getGender(), customer.getEmail(), customer.getPhoneNumber(), customer.getAddress(), customer.getCountry(), customer.getNativeCountry(), customer.getRole(), customer.getUsername());
        return new ResponseEntity<>(customerDto,HttpStatus.OK);

}
    @GetMapping("/checkSession")
    public String checkSession(HttpSession session) {
        if (session != null && session.getAttribute("JSESSIONID") != null) {
            return "Session is active";
        }
        return "Session is inactive";
    }

}
