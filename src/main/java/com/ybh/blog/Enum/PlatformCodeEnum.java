package com.ybh.blog.Enum;
/**
 * @description:
 * @author: Altria-LS
 **/
public enum PlatformCodeEnum {
    SUCCESS("10000", "成功响应"),
    SC_UNAUTHORIZED("401", "没有认证"),
    SC_FORBIDDEN("403", "没有权限"),

    SYSTEM_ERROR("90000", "系统错误"),
    PARAM_IS_INVALID("90001", "非法参数"),
    LOCK_TIMEOUT("90002", "锁定超时"),
    LOCK_FAILED("90003", "锁定失败"),
    DUPLICATION_MANIPULATION("90004", "重复操作"),
    RESULT_IS_NULL("90005", "结果为空"),
    SAVE_ERROR("90006", "保存失败"),
    DELETE_ERROR("90007", "删除失败"),
    PARAM_IS_NULL("90008", "参数为空"),
    RECORD_IS_NULL("90009", "暂无记录"),
    PASSWORD_EXPIRY("90010", "密码超过180天未更新！，请点击忘记密码修改"),
    METHOD_UN_IMPLEMENT("90011", "方法未实现"),

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
