package com.example.blog.service;

import com.example.blog.pojo.request.UserLoginRequest;
import com.example.blog.pojo.response.UserInfoResponse;
import com.example.blog.pojo.response.UserLoginResponse;

public interface UserService {
    UserLoginResponse checkPassword(UserLoginRequest userLoginRequest);

    UserInfoResponse getUserInfo(Integer userId);

    UserInfoResponse getAuthorInfo(Integer blogId);
}
