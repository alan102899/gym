package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //查询所有商品
    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> getAll(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            List<Product> productList = productService.getAll();
            map.put("productList",productList);
        }catch(Exception e){
            e.printStackTrace();
            map.put("message","get product message error!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
        return ResponseEntity.ok(map);
    }

    //根据名称查询对应商品
    @GetMapping("/name/{name}")
    public ResponseEntity<Map<String,Object>> getAll(HttpServletRequest request,@PathVariable String name){
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            List<Product> productList = productService.getByName(name);
            map.put("productList",productList);
        }catch(Exception e){
            e.printStackTrace();
            map.put("message","get product message error!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
        return ResponseEntity.ok(map);
    }

    //新增商品
    @PostMapping("/")
    public ResponseEntity<Map<String,Object>> save(@RequestBody Product product) {
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            Product productByPid = productService.findByPid(product.getPid());
            if(productByPid!=null){
                map.put("message","Product Id already exists!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            Product save = productService.save(product);
            map.put("message","Save Product Information Successfully!");
            map.put("product",save);
            return ResponseEntity.ok(map);
        } catch(Exception e){
            e.printStackTrace();
            map.put("message","Save Product Information error!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
    }
    
}
