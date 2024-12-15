package com.example.demo.service;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Repository.MemberRepository;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;


    public Member create(Member member) {
        return memberRepository.save(member);
    }

    public Member findByMid(String mid){
        return memberRepository.findByMid(mid);
    }



    public List<Member> findByName(String name){
        return memberRepository.findByFirstnameContainingOrLastnameContaining(name,name);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    @Transactional
    public void delete(String mid){
        memberRepository.deleteByMid(mid);
    }

    @Transactional
    public boolean topUp(String mid,double money){
        Member member = memberRepository.findByMid(mid);
        try{
            if (member!= null) {
                member.setBalance(member.getBalance()+money);
                memberRepository.save(member);
                return true;
            }else{
                return false;
            }
        }catch(Exception e) {
            return false;
        }
    }


    @Transactional
    public boolean update(Member member){
        Member old_member = memberRepository.findByMid(member.getMid());
        try{
            if (old_member!= null) {
                memberRepository.save(member);
                return true;
            }else{
                return false;
            }
        }catch(Exception e) {
            return false;
        }
    }
}