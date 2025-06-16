package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blog.common.exception.BlogException;
import com.example.blog.common.util.JwtUtils;
import com.example.blog.mapper.UserInfoMapper;
import com.example.blog.pojo.dataobject.UserInfo;
import com.example.blog.pojo.request.UserLoginRequest;
import com.example.blog.pojo.response.UserLoginResponse;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public UserLoginResponse checkPassword(UserLoginRequest userLoginRequest) {
        QueryWrapper<UserInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(UserInfo::getUserName,userLoginRequest.getUserName())
                .eq(UserInfo::getDeleteFlag,0);
        UserInfo userInfo=userInfoMapper.selectOne(queryWrapper);
        if (userInfo==null){
            throw new BlogException("用户不存在");
        }
        if (!userLoginRequest.getPassword().equals(userInfo.getPassword())){
            throw new BlogException("密码错误");
        }
        Map<String,Object> map=new HashMap<>();
        map.put("id",userInfo.getId());
        map.put("name",userInfo.getUserName());
        String token= JwtUtils.genToken(map);
        return new UserLoginResponse(userInfo.getId(),token);
    }
}
