package com.ccut.main.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class userLoginRequest implements Serializable {

    private static final long serialVersionUID = -9103160605890663173L;
    private   String userAccount ;
    private   String password;
}
