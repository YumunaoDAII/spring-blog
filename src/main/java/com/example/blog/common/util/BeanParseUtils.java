package com.example.blog.common.util;

import com.example.blog.pojo.dataobject.BlogInfo;
import com.example.blog.pojo.response.BlogInfoResponse;
import org.springframework.beans.BeanUtils;

public class BeanParseUtils {
    public static BlogInfoResponse trains(BlogInfo blogInfo){
        if (blogInfo==null){
            //TODO 待做事项
            return null;
        }
        BlogInfoResponse response = new BlogInfoResponse();
        BeanUtils.copyProperties(blogInfo,response);
        return response;
    }
}
