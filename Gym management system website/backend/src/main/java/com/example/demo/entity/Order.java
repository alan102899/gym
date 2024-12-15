package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Oid")
    private Integer Oid;
    @ManyToOne
    @JoinColumn(name = "Pid", nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "Mid", nullable = false)
    private Member member;
    @Column(name = "createtime")
    private Date createtime;

}
