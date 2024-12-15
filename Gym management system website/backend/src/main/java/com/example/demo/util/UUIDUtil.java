package com.example.demo.util;

import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class UUIDUtil {
    public static String getUUID(){
        String id = UUID.randomUUID().toString();
        String uid = id.replaceAll("-", "");
        return uid;
    }

    //测试
    public static void main(String[] args) {
        String uid = UUIDUtil.getUUID();
        System.out.println("===="+uid);
        System.out.println("===="+UUIDUtil.getUUID());
        System.out.println("===="+UUIDUtil.getUUID());
    }

}
