package com.ccut.main.Exception;

import com.ccut.main.common.BaseResponse;
import com.ccut.main.common.coedError;
import com.ccut.main.common.responseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 集中捕获异常,不暴露后端信息
 * 封装好异常
 */
@RestControllerAdvice
@Slf4j
public class globalExceptionHandle {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessExceptionHandle(BusinessException businessException){
        log.info("businessException"+businessException.getMessage());
        return responseUtils.error(businessException.getCode(),businessException.getMessage(),businessException.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse businessExceptionHandle(RuntimeException runtimeException,String description){
        log.info("runtimeException",runtimeException);
        return responseUtils.error(coedError.SYSTEM_EX,runtimeException.getMessage(),description);
    }

}
