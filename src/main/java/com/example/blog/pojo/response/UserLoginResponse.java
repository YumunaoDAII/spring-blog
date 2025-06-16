package com.example.blog.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class UserLoginResponse {

    private Integer userId;
    private String token;
}
