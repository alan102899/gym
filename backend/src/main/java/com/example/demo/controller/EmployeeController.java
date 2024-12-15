package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.util.UUIDUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Data
class LoginData{
    private String username;
    private String password;
}


@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    //注册
    @PostMapping("/register")
    public ResponseEntity<Map<String,Object>> register(@RequestBody Employee employee) {
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            String eid = UUIDUtil.getUUID();
            employee.setEid(eid);
            Employee employeeByUsername = employeeService.findByUsername(employee.getUsername());
            if(employeeByUsername!= null){
                map.put("message","Username already exists!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            Employee employeeByEid = employeeService.findByEid(employee.getEid());
            if(employeeByEid != null){
                map.put("message","Eid already exists!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            Employee register = employeeService.create(employee);
            map.put("message", "Register success.");
            map.put("employee", register);
        } catch(Exception e){
            e.printStackTrace();
            map.put("message","register member error!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
        return ResponseEntity.ok(map);
    }

    //登录
    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody LoginData loginData) {
        Map<String,Object> map=new HashMap<String, Object>();
        try{
            Employee employee = employeeService.login(loginData.getUsername(),loginData.getPassword());
            if(employee.getEid()==null){
                map.put("message","Incorrect username or password.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
            }
            map.put("message","Login success.");
            map.put("employee",employee);
        }catch (NullPointerException e){
            map.put("message","Incorrect username or password.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }catch(Exception e){
            e.printStackTrace();
            map.put("message","login member error!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
        return ResponseEntity.ok(map);
    }


}
