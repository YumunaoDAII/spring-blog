package com.example.blog.pojo.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserLoginRequest {
    @NotNull
    @Length(max = 20,min = 5)
    private String userName;
    @NotNull
    @Length(min = 5, message = "密码长度不能小于5")
    private String password;
}
