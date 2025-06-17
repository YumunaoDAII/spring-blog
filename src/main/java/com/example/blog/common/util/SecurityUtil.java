package com.example.blog.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
@Slf4j
public class SecurityUtil {
    /**
     * 加密
     * @return 盐值+密文
     */
    public static String encrypt(String password){
        String salt= UUID.randomUUID().toString().replace("-","");
        String securityPassword = DigestUtils
                .md5DigestAsHex((salt + password).getBytes(StandardCharsets.UTF_8));
        return salt+securityPassword;
    }
    public static boolean verify(String inputPassword,String sqlPassword){
        log.info("登录参数校验, inputPassword:{},sqlPassword:{}",inputPassword,sqlPassword);
        if (!StringUtils.hasLength(inputPassword)){
            return false;
        }
        if (sqlPassword==null||sqlPassword.length()!=64){
            log.warn("sqlPassword长度错误");
            return false;
        }
        String salt = sqlPassword.substring(0, 32);
        String securityPassword = DigestUtils.md5DigestAsHex((salt + inputPassword).getBytes(StandardCharsets.UTF_8));
        return sqlPassword.equals(salt+securityPassword);
    }

}
