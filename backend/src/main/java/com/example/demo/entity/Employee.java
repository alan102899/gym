package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @Column(name = "Eid")
    private String eid;
    @Column(name = "Username")
    private String username;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;
}
