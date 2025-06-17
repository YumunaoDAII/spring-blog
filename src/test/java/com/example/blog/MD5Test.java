package com.example.blog;

import com.example.blog.common.util.SecurityUtil;
import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class MD5Test {
    @Test
    void MD5(){
        String password="123456";
        String s = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        System.out.println(s);
        String salt = UUID.randomUUID().toString().replace("-", "");
        String s2 = DigestUtils.md5DigestAsHex((salt + password).getBytes(StandardCharsets.UTF_8));
        System.out.println(s2);
    }
    @Test
    void check(){
        String sqlPassword="e10adc3949ba59abbe56e057f20f883eb3c182c4728d3819d7bd9fd1bfb73c82";
    }
    @Test
    void Test(){
        String encrypt = SecurityUtil.encrypt("123456");
        System.out.println(encrypt);
    }
}
