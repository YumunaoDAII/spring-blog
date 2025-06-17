package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blog.common.exception.BlogException;
import com.example.blog.common.util.BeanTransUtils;
import com.example.blog.common.util.JwtUtils;
import com.example.blog.common.util.SecurityUtil;
import com.example.blog.mapper.UserInfoMapper;
import com.example.blog.pojo.dataobject.BlogInfo;
import com.example.blog.pojo.dataobject.UserInfo;
import com.example.blog.pojo.request.UserLoginRequest;
import com.example.blog.pojo.response.BlogInfoResponse;
import com.example.blog.pojo.response.UserInfoResponse;
import com.example.blog.pojo.response.UserLoginResponse;
import com.example.blog.service.BlogService;
import com.example.blog.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource(name = "blogServiceImpl")
    private BlogService blogService;
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
//        if (!userLoginRequest.getPassword().equals(userInfo.getPassword())){
//            throw new BlogException("密码错误");
//        }
        if (!SecurityUtil.verify(userLoginRequest.getPassword(),userInfo.getPassword())){
            throw new BlogException("密码错误");
        }
        Map<String,Object> map=new HashMap<>();
        map.put("id",userInfo.getId());
        map.put("name",userInfo.getUserName());
        String token= JwtUtils.genToken(map);
        return new UserLoginResponse(userInfo.getId(),token);
    }

    @Override
    public UserInfoResponse getUserInfo(Integer userId) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserInfo::getDeleteFlag,0).eq(UserInfo::getId,userId);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        return BeanTransUtils.trans(userInfo);
    }

    @Override
    public UserInfoResponse getAuthorInfo(Integer blogId) {
        BlogInfo blogInfo = blogService.getBlogInfo(blogId);
        if (blogInfo==null||blogInfo.getUserId()<=0){
            throw new BlogException("博客不存在");
        }
        return getUserInfo(blogInfo.getUserId());
    }
}
