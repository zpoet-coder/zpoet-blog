package cn.zpoet.blog.entity.common;

import cn.zpoet.blog.enums.common.ResultCode;
import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    // 成功返回（带数据）
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    // 成功返回（不带数据）
    public static <T> Result<T> success() {
        return success(null);
    }

    // 失败返回
    public static <T> Result<T> failure(ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setData(null);
        return result;
    }

    // 自定义失败返回（比如 message 自定义）
    public static <T> Result<T> failure(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }
}
