package com.ybh.blog.exception;

/**
 * @Author:za-yubohan
 * @Date:2022/8/31 18:05
 */
public class BaseException extends RuntimeException {

    /**
     * @Description: 异常构造方法 在使用时方便传入错误码和信息
     * @Author: za-yubohan
     **/
    public BaseException(String msg){
        super(msg);
    }
}
