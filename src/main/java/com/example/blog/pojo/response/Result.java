package com.example.blog.pojo.response;



import com.example.blog.enums.ResultCodeEnum;
import lombok.Data;

@Data
public class Result {
    private ResultCodeEnum code;//业务状态码, 非Http状态码
    private String errMsg;
    private Object data;

    public static Result success(Object data){
        Result result = new Result();
        result.setCode(ResultCodeEnum.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result fail(String errMsg){
        Result result = new Result();
        result.setCode(ResultCodeEnum.FAIL);
        result.setErrMsg(errMsg);
        return result;
    }

    public static Result fail(String errMsg, Object data){
        Result result = new Result();
        result.setCode(ResultCodeEnum.FAIL);
        result.setErrMsg(errMsg);
        result.setData(data);
        return result;
    }

}
