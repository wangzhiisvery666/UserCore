package com.ccut.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccut.main.bean.UserTable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
* @author o'k
* @description  针对表【usertable】的数据库操作Service
* @createDate  2022-07-06 11:47:05
*/
public interface UserTableService extends IService<UserTable> {


    /**
     * 验证方法
     * @param userAccount 用户名
     * @param password 用户密码
     * @param checkCode 验证密码
     */
    boolean register(String userAccount , String password ,String checkCode);

    /**
     * 登陆方法
     * @param userAccount 用户名
     * @param password 用户密码
     * @param request  用于获取session
     * @return 返回用户对象
     */
    boolean doLogin(String userAccount , String password, HttpServletRequest request);

    /**
     * 脱敏方法
     * @param user ：用户名
     * @return ：返回脱敏后的对象
     */
    UserTable getSafetyUser (UserTable  user);

    /**
     * 注销方法
     * @param session :用于判断是否登陆
     */
    void logout(HttpSession session );

}
