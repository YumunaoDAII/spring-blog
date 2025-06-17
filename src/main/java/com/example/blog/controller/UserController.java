package com.example.blog.controller;

import com.example.blog.pojo.request.UserLoginRequest;
import com.example.blog.pojo.response.UserInfoResponse;
import com.example.blog.pojo.response.UserLoginResponse;
import com.example.blog.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
    @Resource(name = "userServiceImpl")
    private UserService userService;
    @RequestMapping("/login")
    public UserLoginResponse login(@RequestBody @Validated UserLoginRequest userLoginRequest){
        log.info("用户登录，用户名："+userLoginRequest.getUserName());
        return userService.checkPassword(userLoginRequest);
    }
    @RequestMapping("/getUserInfo")
    public UserInfoResponse getUserInfo(@NotNull(message = "userId不能为null") Integer userId){
        log.info("获取用户信息，userId:"+userId);
        return userService.getUserInfo(userId);
    }
    @RequestMapping("/getAuthorInfo")
    public UserInfoResponse getAuthorInfo(@NotNull(message = "blogId不能为null") Integer blogId){
        log.info("获取用户信息，blogId:"+blogId);
        return userService.getAuthorInfo(blogId);
    }
}
