package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Member")
public class Member{
    @Id
    @Column(name = "Mid")
    private String mid;
    @Column(name = "Firstname")
    private String firstname;
    @Column(name = "Lastname")
    private String lastname;
    @Column(name = "Birthday")
    private String birthday;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Email")
    private String email;
    @Column(name = "Address")
    private String address;
    @Column(name = "Balance")
    private double balance;

}
