package com.example.blog.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Map;
@Slf4j
public class JwtUtils {
    private static String SELECT_STRING="7NO5R+1aI1bWhht/vkrArchUl2C+Un+i75wKuXC3CO8=";
    private static Key key = Keys.hmacShaKeyFor(SELECT_STRING.getBytes(StandardCharsets.UTF_8));
    public static String genToken(Map<String,Object> claims){
        String compact = Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();
        System.out.println(compact);
        log.info("compact:   "+compact);
        return compact;
    }
    public static Claims parseToken(String token){
        if (!StringUtils.hasLength(token)){
            return null;
        }
        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
        Claims claims=null;
        try {
            claims = build.parseClaimsJws(token).getBody();
        }catch (Exception e){
             log.error("Token解析失败，token ："+token);
        }
        return claims;
    }
}
