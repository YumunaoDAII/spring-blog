package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blog.common.util.BeanParseUtils;
import com.example.blog.mapper.BlogInfoMapper;
import com.example.blog.pojo.dataobject.BlogInfo;
import com.example.blog.pojo.response.BlogInfoResponse;
import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogInfoMapper blogInfoMapper;
    @Override
    public List<BlogInfoResponse> getList() {
        QueryWrapper<BlogInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogInfo::getDeleteFlag,0);
        List<BlogInfo> blogInfos = blogInfoMapper.selectList(queryWrapper);

        List<BlogInfoResponse> blogInfoResponses= blogInfos.stream().map(blogInfo-> BeanParseUtils.trains(blogInfo)
        ).collect(Collectors.toList());
        return blogInfoResponses;
    }

    @Override
    public BlogInfoResponse getBlogDetail(Integer blogId) {
        QueryWrapper<BlogInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogInfo::getDeleteFlag,0).eq(BlogInfo::getId,blogId);
        BlogInfo blogInfo = blogInfoMapper.selectOne(queryWrapper);
        return BeanParseUtils.trains(blogInfo);
    }
}
