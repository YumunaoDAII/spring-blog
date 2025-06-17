package com.example.blog.common.interceptor;

import com.example.blog.common.constant.Constants;
import com.example.blog.common.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userToken = request.getHeader(Constants.USER_TOKEN_HEADER);
        log.info("Token  :"+userToken);
        if (userToken==null){
            response.setStatus(401);
            return false;
        }
        Claims claims = JwtUtils.parseToken(userToken);
        if (claims==null){
            response.setStatus(401);
            return false;
        }
        return true;
    }
}
