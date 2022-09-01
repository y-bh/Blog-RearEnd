package com.ybh.blog.exception;

import com.ybh.blog.VO.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author:za-yubohan
 * @Date:2022/9/1 18:51
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * @Description: 处理自定义的业务异常
     * @Author: za-yubohan
     **/
    @ExceptionHandler(value = BaseException.class)
    public Result<String> BizExceptionHandler(BaseException e){
        log.error("业务异常：->",e);
        return Result.error(e.getMessage());
    }

    /**
     * @Description: 处理空指针异常
     * @Author: za-yubohan
     **/
    @ExceptionHandler(value = NullPointerException.class)
    public Result<String> exceptionHandler(NullPointerException e) {
        log.error("发生空指针异常！ msg: -> ", e);
        return Result.error("发生空指针异常!");
    }

    /**
     * @Description: 服务器异常
     * @Author: za-yubohan
     **/
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> exception(Exception e) {
        log.error("服务器异常！ msg: -> ", e);
        return Result.error("服务器异常!");
    }
}
