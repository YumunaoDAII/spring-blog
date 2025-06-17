package com.example.blog.common.util;

import com.example.blog.pojo.dataobject.BlogInfo;
import com.example.blog.pojo.dataobject.UserInfo;
import com.example.blog.pojo.response.BlogInfoResponse;
import com.example.blog.pojo.response.UserInfoResponse;
import org.springframework.beans.BeanUtils;

public class BeanTransUtils {
    public static BlogInfoResponse trans(BlogInfo blogInfo){
        if (blogInfo==null){
            //TODO 待做事项
            return null;
        }
        BlogInfoResponse response = new BlogInfoResponse();
        BeanUtils.copyProperties(blogInfo,response);
        return response;
    }
    public static UserInfoResponse trans(UserInfo userInfo){
        if (userInfo==null){
            //TODO 待做事项
            return null;
        }
        UserInfoResponse userInfoResponse=new UserInfoResponse();
        BeanUtils.copyProperties(userInfo,userInfoResponse);
        return userInfoResponse;
    }
}
