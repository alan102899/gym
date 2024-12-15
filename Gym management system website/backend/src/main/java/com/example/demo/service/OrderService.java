package com.example.demo.service;

import com.example.demo.Repository.MemberRepository;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.entity.Member;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProductRepository productRepository;

    public Order save(String pid,String mid){
        Product product = productRepository.findByPid(pid);
        Member member = memberRepository.findByMid(mid);
        if(member.getBalance()>=product.getPrice()){
            member.setBalance(member.getBalance()-product.getPrice());
            Member saveMember = memberRepository.save(member);
            Order order=new Order();
            order.setCreatetime(new Date());
            order.setProduct(product);
            order.setMember(saveMember);
            return orderRepository.save(order);
        }else {
            return null;
        }
    }
    @Transactional
    public void deleteMid(String mid){
        Member member=new Member();
        member.setMid(mid);
        orderRepository.deleteByMember(member);
    }
}