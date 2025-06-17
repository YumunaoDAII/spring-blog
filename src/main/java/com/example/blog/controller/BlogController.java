package com.example.blog.controller;


import com.example.blog.pojo.request.AddBlogRequest;
import com.example.blog.pojo.request.UpdateBlogRequest;
import com.example.blog.pojo.response.BlogInfoResponse;
import com.example.blog.service.BlogService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
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
    @RequestMapping("/add")
    public Boolean addBlog(@RequestBody @Validated AddBlogRequest addBlogRequest){
        log.info("发布博客,userId:{},title:{}",addBlogRequest.getUserId(),addBlogRequest.getTitle());
        return blogService.addBlog(addBlogRequest);
    }
    @RequestMapping("/update")
    public Boolean update(@RequestBody @Validated UpdateBlogRequest updateBlogRequest){
        log.info("更新博客,request:{}",updateBlogRequest);
        return blogService.updateBlog(updateBlogRequest);
    }
    @RequestMapping("/delete")
    public Boolean delete(@NotNull(message = "id不能为null") Integer blogId){
        log.info("删除博客，id:{}",blogId);
        return blogService.delete(blogId);
    }
}
