package com.ccut.main.model.request;


import lombok.Data;

import java.io.Serializable;

@Data
public class userRegisterRequest implements Serializable {

    private static final long serialVersionUID = -3576985735516995831L;
    private String userAccount;
    private String password ;
    private String checkCode;
}
