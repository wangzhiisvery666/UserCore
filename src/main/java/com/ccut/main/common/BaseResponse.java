package com.ccut.main.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装返回信息的类，包括状态码
 * @param <T>
 */
@Data
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 4800197661604594897L;

    private  String message;
    private  T data;
    private  int code;
    private  String description;

    public BaseResponse(String message, T data, int code, String description) {
        this.message = message;
        this.data = data;
        this.code = code;
        this.description = description;
    }

    public BaseResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }
    public BaseResponse(T data, String message) {
        this.message = message;
        this.data = data;

    }
    public BaseResponse(T data, String message,String description) {
        this.message = message;
        this.data = data;
        this.description = description;
    }
    public BaseResponse(coedError coedError,T data) {
        this(coedError.getMessage(),data,coedError.getCode(),"");
    }

    public BaseResponse(coedError coedError) {
        this(coedError.getMessage(),null,coedError.getCode(),coedError.getDescribe());

    }
    public BaseResponse(coedError coedError,String description) {
        this(coedError.getMessage(),null,coedError.getCode(),description);

    }
    public BaseResponse(coedError coedError,String message,String description) {
        this(message,null,coedError.getCode(),description);

    }
}
