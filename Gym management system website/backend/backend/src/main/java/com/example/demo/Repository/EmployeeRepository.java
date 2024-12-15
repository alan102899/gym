package com.example.demo.Repository;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUsernameAndPassword(String username,String password);
    Employee findByUsername(String username);


    Employee findByEid(String eid);
    void deleteByEid(String eid);
    List<Employee> findAll();
}
