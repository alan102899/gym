package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @Column(name = "Pid")
    private String pid;
    @Column(name = "Name")
    private String name;
    @Column(name = "Price")
    private double price;
}
