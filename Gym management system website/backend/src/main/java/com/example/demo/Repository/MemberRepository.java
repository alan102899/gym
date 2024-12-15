package com.example.demo.Repository;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByFirstnameContainingOrLastnameContaining(String firstnameKeyword, String lastnameKeyword);
    Member findByMid(String mid);
    void deleteByMid(String mid);
    List<Member> findAll();
}
