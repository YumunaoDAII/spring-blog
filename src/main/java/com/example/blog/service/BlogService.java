package com.example.blog.service;

import com.example.blog.pojo.dataobject.BlogInfo;
import com.example.blog.pojo.request.AddBlogRequest;
import com.example.blog.pojo.response.BlogInfoResponse;

import java.util.List;

public interface BlogService {

    List<BlogInfoResponse> getList();

    BlogInfoResponse getBlogDetail(Integer blogId);

    BlogInfo getBlogInfo(Integer blogId);

    Boolean addBlog(AddBlogRequest addBlogRequest);
}
