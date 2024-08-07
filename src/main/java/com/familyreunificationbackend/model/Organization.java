package com.familyreunificationbackend.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
public class Organization {
@Id @GeneratedValue(strategy = GenerationType.AUTO)
private long id;
private String name;
@Lob
private byte[] logo;
private String description;
private String address;
private LocalDateTime timeStamp;
@ManyToOne(cascade = CascadeType.ALL,targetEntity =Customer.class)
private Customer customer;
@OneToMany(cascade = CascadeType.ALL,targetEntity =Beneficiary.class,mappedBy ="organization" )
private List<Beneficiary>beneficiaryList;
public String getTimeStamp(){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss a");
    return formatter.format(timeStamp);
}
public String getLogo(){
    return "data:image/png;base64,"+Base64.getEncoder().encodeToString(logo);
}
public Organization(long id,String name,String logo,String description,String address,Customer customer){
this.id=id;
this.name=name;
String image=logo.split("base64,")[1];
this.logo=Base64.getDecoder().decode(image);
this.description=description;
this.address=address;
this.timeStamp=LocalDateTime.now();
this.customer=customer;
}
}
