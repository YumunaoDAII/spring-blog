package com.example.blog.pojo.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class UserInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String githubUrl;
    private Integer deleteFlag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
