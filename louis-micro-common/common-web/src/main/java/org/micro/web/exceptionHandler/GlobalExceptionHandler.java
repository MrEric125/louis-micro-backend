package org.micro.web.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.micro.common.api.wrapper.MapperWrap;
import org.micro.common.api.wrapper.Wrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * @author John·Louis
 * @date created on 2020/3/9
 * description:
 * 全局异常处理功能
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Wrapper exceptionHandler(Exception e) {
        log.error("异常message:{}", e.getMessage());
        e.printStackTrace();
        return MapperWrap.wrap(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }
}
