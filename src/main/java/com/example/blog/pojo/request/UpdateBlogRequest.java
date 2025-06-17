package com.example.blog.pojo.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateBlogRequest {
    @NotNull(message = "id不能为null")
    private Integer id;
    private String title;
    private String content;
}
