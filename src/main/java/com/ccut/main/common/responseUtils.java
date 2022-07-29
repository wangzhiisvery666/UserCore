package com.ccut.main.common;

public class responseUtils {

    public  static <T> BaseResponse<T> success(T data){

        return new BaseResponse<>(data,"OK");
    }

    public  static <T> BaseResponse<T> error(int code,T data,String message,String description){

        return new BaseResponse<>(message,data,code,description);
    }
    public  static <T> BaseResponse<T> error(int code,String message,String description){

        return new BaseResponse<>(message,null,code,description);
    }
    public  static <T> BaseResponse<T> error(coedError coedError){

        return new BaseResponse<>(coedError);
    }
    public  static <T> BaseResponse<T> error(coedError coedError,String description){

        return new BaseResponse<>(coedError,description);
    }
    public  static <T> BaseResponse<T> error(coedError coedError,String message,String description){

        return new BaseResponse<>(coedError,message,description);
    }
}
