package com.ybh.blog.Enum;
/**
 * @description:
 * @author: Altria-LS
 **/
public enum PlatformCodeEnum {
    SUCCESS("200", "成功响应"),
    SC_UNAUTHORIZED("401", "用户未登录，请登录后操作"),
    SC_FORBIDDEN("403", "token已过期,请重新登录"),

    SYSTEM_ERROR("500", "系统错误"),
    PARAM_IS_INVALID("501", "非法参数"),
    LOCK_TIMEOUT("502", "锁定超时"),
    LOCK_FAILED("503", "锁定失败"),
    ALREADY_EXIST("504", "信息已存在"),
    RESULT_IS_NULL("505", "结果为空"),
    SAVE_ERROR("506", "保存失败"),
    DELETE_ERROR("507", "删除失败"),
    PARAM_IS_NULL("508", "参数为空"),
    RECORD_IS_NULL("509", "暂无记录"),
    REDIS_ERROR("510","寄，redis出问题了"),
    VERIFY_NULL("511","验证码为空");

    ;


    private String code;
    private String value;

    private PlatformCodeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
