package com.familyreunificationbackend.controller;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.familyreunificationbackend.dto.CustomerDTO;
import com.familyreunificationbackend.enums.Role;
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
        if (!auth.getName().equals("anonymous")) {
            Cookie cookie = new Cookie("home", "null");
            response.addCookie(cookie);
        }

        return new String();
    }
    private static long organizationId;
    private static String organizationName;
    @PostMapping(value = "/success-login")
    public ResponseEntity<CustomerDTO> getMethodName(Principal principal, HttpServletRequest session) {
        Customer customer = customerServices.findByUsername(principal.getName());
        log.info("Session generated=", session.getSession().getId());
        log.info("Session generated=", session.getSession().getId());
        log.info("User name =", principal.getName());
         organizationId=0;
        if(customer.getRole().equals(Role.ROLE_PATNER)){
            customer.getOrganization().stream().forEach(
                i->{
                    organizationId=i.getId();
                    organizationName=i.getName();
                    return;
                }
            );
        }
        CustomerDTO customerDto=new CustomerDTO(customer.getId(), customer.getFirstName(), customer.getLastName(),
         null, customer.getGender(), customer.getEmail(), customer.getPhoneNumber(), customer.getAddress(),
          customer.getCountry(), customer.getNativeCountry(), customer.getRole(), customer.getUsername(),organizationId,organizationName);
        return new ResponseEntity<>(customerDto,HttpStatus.OK);

}
    @GetMapping("/checkSession")
    public String checkSession(Principal principal) {
        log.info("information {}",principal);
     return principal.getName();
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpSession session) {
        SecurityContextHolder.clearContext();
        session.invalidate();
        return "redirect:/login";
    }
    @PostMapping("/signout")
    public String logout(HttpSession session,Principal principal) {
        session.invalidate();
        log.info("logout handler {}",principal);
        SecurityContextHolder.clearContext();
        log.info("logout session handler {}",session.getId());
        log.info("logout handler {}",principal);
        return "redirect:/login";
    }
}
