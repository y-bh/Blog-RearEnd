package com.ybh.blog.Enum;
/**
 * @description:
 * @author: Altria-LS
 **/
public enum PlatformCodeEnum {
    SUCCESS("10000", "成功响应"),
    ERROR("9999","系统错误");


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
