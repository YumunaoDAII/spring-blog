package com.example.blog.controller;


import com.example.blog.pojo.response.BlogInfoResponse;
import com.example.blog.service.BlogService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RequestMapping("/blog")
@RestController
public class BlogController {
    @Resource(name = "blogServiceImpl")
    private BlogService blogService;
    @RequestMapping("/getList")
    public List<BlogInfoResponse> getList(){
        log.info("获取博客列表。。。。");
        List<BlogInfoResponse> blogInfos= blogService.getList();

        return blogInfos;
    }
    @RequestMapping("/getBlogDetail")
    public BlogInfoResponse getBlogDetail(@NotNull(message = "blogId不能为null") Integer blogId){
        log.info("获取博客详情,   blogId:{}",blogId);
        return blogService.getBlogDetail(blogId);
    }
}
