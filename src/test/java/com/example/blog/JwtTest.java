package com.example.blog;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    void genToken(){
//        Key key = Keys.hmacShaKeyFor("kGmiTrem5gU1+BDOlwPssDpkP50fNObF/wygI8oEPTk=".getBytes(StandardCharsets.UTF_8));
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 12);
        claims.put("name", "zhangsan");

        String compact = Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();
        System.out.println(compact);

//        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
//        System.out.println(build.parse(compact+"1").getBody());
    }

    @Test
    void genKey(){
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String encode = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(encode);
    }

}