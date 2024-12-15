package com.example.demo.service;

import com.example.demo.Repository.ProductRepository;
import com.example.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public List<Product> getByName(String name){
        return productRepository.findByNameContaining(name);
    }

    public Product findByPid(String pid){
        return productRepository.findByPid(pid);
    }
}