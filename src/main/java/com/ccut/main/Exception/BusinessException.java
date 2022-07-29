package com.ccut.main.Exception;

import com.ccut.main.common.coedError;

public class BusinessException extends RuntimeException {

    private int code;
    private String description;

    public BusinessException(String description, int code, String message) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(coedError coedError) {
        super(coedError.getMessage());
        this.code = coedError.getCode();
        this.description = coedError.getDescribe();
    }

    public BusinessException(coedError coedError, String description) {
        super(coedError.getMessage());
        this.code = coedError.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
