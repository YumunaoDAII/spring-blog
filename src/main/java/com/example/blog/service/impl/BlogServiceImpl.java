package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blog.common.exception.BlogException;
import com.example.blog.common.util.BeanTransUtils;
import com.example.blog.mapper.BlogInfoMapper;
import com.example.blog.pojo.dataobject.BlogInfo;
import com.example.blog.pojo.request.AddBlogRequest;
import com.example.blog.pojo.request.UpdateBlogRequest;
import com.example.blog.pojo.response.BlogInfoResponse;
import com.example.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {
    private static final Integer BLOG_DELETE=1;
    private static final Integer BLOG_NORMAL=0;
    @Autowired
    private BlogInfoMapper blogInfoMapper;
    @Override
    public List<BlogInfoResponse> getList() {
        QueryWrapper<BlogInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogInfo::getDeleteFlag,BLOG_NORMAL);
        List<BlogInfo> blogInfos = blogInfoMapper.selectList(queryWrapper);

        List<BlogInfoResponse> blogInfoResponses= blogInfos.stream().map(blogInfo-> BeanTransUtils.trans(blogInfo)
        ).collect(Collectors.toList());
        return blogInfoResponses;
    }

    @Override
    public BlogInfoResponse getBlogDetail(Integer blogId) {
        return BeanTransUtils.trans(getBlogInfo(blogId));
    }
    @Override
    public BlogInfo getBlogInfo(Integer blogId){
        QueryWrapper<BlogInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogInfo::getDeleteFlag,BLOG_NORMAL).eq(BlogInfo::getId,blogId);
        BlogInfo blogInfo = blogInfoMapper.selectOne(queryWrapper);
        return blogInfo;
    }

    @Override
    public Boolean addBlog(AddBlogRequest addBlogRequest) {
        BlogInfo blogInfo = new BlogInfo();
        BeanUtils.copyProperties(addBlogRequest,blogInfo);
        try {
            Integer result = blogInfoMapper.insert(blogInfo);
            if (result==1){
                return true;
            }
            return false;
        }catch (Exception e){
            log.error("博客发布失败, e:",e);
            throw new BlogException("内部错误，请联系管理员");
        }
    }

    @Override
    public Boolean updateBlog(UpdateBlogRequest updateBlogRequest) {
        BlogInfo blogInfo = BeanTransUtils.trans(updateBlogRequest);
        try {
            int result = blogInfoMapper.updateById(blogInfo);
            return result==1;
        }catch (Exception e){
            log.error("更新失败，e:",e);
            throw new BlogException("内部错误，请联系管理员");
        }
    }

    @Override
    public Boolean delete(Integer blogId) {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setId(blogId);
        blogInfo.setDeleteFlag(BLOG_DELETE);
        try {
            int result = blogInfoMapper.updateById(blogInfo);
            return result==1;
        }catch (Exception e){
            log.error("更新失败，e:",e);
            throw new BlogException("内部错误，请联系管理员");
        }
    }
}
