package com.ybh.blog.VO;

import com.ybh.blog.Enum.PlatformCodeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 封装实体类
 * @Author: Altria-LS
 * @CreateTime: 2022-08-24  21:14
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态信息，正确返回OK，否则返回 ERROR，如果返回ERROR则需要填写Message信息
     */
    private Status status = Status.OK;

    public enum Status {
        OK,
        ERROR
    }

    /**
     * 返回码
     */
    private String code = PlatformCodeEnum.SUCCESS.getCode();

    /**
     * 提示信息
     */
    private String message = PlatformCodeEnum.SUCCESS.getValue();

    /**
     * 数据对象
     */
    private T data;

    /**
     * 时间戳
     */
    private Long date = new Date().getTime();

    private boolean success;

    public Result<T> Message(String message) {
        this.message = message;
        return this;
    }

    public Result<T> Status(Status status) {
        this.status = status;
        return this;
    }

    public Result<T> Code(String code) {
        this.code = code;
        return this;
    }

    public static <T> Result<T> ok() {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> ok(String message) {
        Result<T> result = new Result<>();
        result.setMessage(message);
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> ok(String message, T data) {
        Result<T> result = new Result<>();
        result.setMessage(message);
        result.setData(data);
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.setStatus(Status.ERROR);
        result.setCode(PlatformCodeEnum.SYSTEM_ERROR.getCode());
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> error(T data) {
        Result<T> result = new Result<>();
        result.setStatus(Status.ERROR);
        result.setCode(PlatformCodeEnum.SYSTEM_ERROR.getCode());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(String code, String message) {
        Result<T> result = new Result<>();
        result.setStatus(Status.ERROR);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> error(String code, String message, T data) {
        Result<T> result = new Result<>();
        result.setStatus(Status.ERROR);
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(String code, T data) {
        Result<T> result = new Result<>();
        result.setStatus(Status.ERROR);
        result.setCode(code);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(PlatformCodeEnum code) {
        Result<T> result = new Result<>();
        result.setStatus(Status.ERROR);
        result.setCode(code.getCode());
        result.setMessage(code.getValue());
        return result;
    }

    public boolean isOk() {
        return Status.OK == this.status ? true : false;
    }

}
