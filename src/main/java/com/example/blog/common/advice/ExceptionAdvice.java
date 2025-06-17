package com.example.blog.common.advice;

import com.example.blog.common.exception.BlogException;
import com.example.blog.pojo.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@ResponseBody
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler
    public Result exceptionHandler(Exception exception){
        log.error("发生异常,e:{}",exception);
        return Result.fail(exception.getMessage());
    }
    @ExceptionHandler
    public Result exceptionHandler(BlogException exception){
        log.error("发生异常,e:{}",exception);
        return Result.fail(exception.getErrMsg());
    }
    @ExceptionHandler
    public Result exceptionHandler(HandlerMethodValidationException exception){
        log.error("发生异常,e:{}",exception.getMessage());
        return Result.fail("参数校验失败");
    }
    @ExceptionHandler
    public Result exceptionHandler(MethodArgumentNotValidException exception){
        String msg = exception.getBindingResult().getFieldError().getDefaultMessage();
        log.error("发生异常,e:{}",msg);
        return Result.fail("参数校验失败");
    }
}
