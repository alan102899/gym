package com.example.demo.service;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findByEid(String eid){
        return employeeRepository.findByEid(eid);
    }


    public Employee login(String username,String password){
        return employeeRepository.findByUsernameAndPassword(username,password);
    }

    public Employee findByUsername(String username){
        return employeeRepository.findByUsername(username);
    }



}