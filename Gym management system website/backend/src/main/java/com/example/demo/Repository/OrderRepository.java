package com.example.demo.Repository;

import com.example.demo.entity.Member;
import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    void deleteByMember(Member member);
}
