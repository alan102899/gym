package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
class PayData{
    private String mid;
    private String pid;
}


@RestController
@CrossOrigin
@RequestMapping("/pay")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("/")
    public ResponseEntity<Map<String,Object>> save(@RequestBody PayData payData) {
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            Order order = orderService.save(payData.getPid(), payData.getMid());
            if(order!=null){
                map.put("message","Pay Successfully!");
                map.put("order",order);
                return ResponseEntity.ok(map);
            }else{
                map.put("message","Insufficient balance.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
        } catch(Exception e){
            e.printStackTrace();
            map.put("message","Pay error!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
    }
    
}
