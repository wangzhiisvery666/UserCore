package com.ccut.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccut.main.Exception.BusinessException;
import com.ccut.main.bean.UserTable;
import com.ccut.main.common.coedError;
import com.ccut.main.mapper.UserTableMapper;
import com.ccut.main.service.UserTableService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

import static com.ccut.main.content.userPermission.LOGIN_STATE_CODE;

/**
* @author wz
* @description 针对表【usertable】的数据库操作Service实现
* @createDate 2022-07-06 11:47:05
*/
@Service
public class UserTableServiceImpl extends ServiceImpl<UserTableMapper, UserTable> implements UserTableService{

    @Resource
    UserTableMapper UTM;

    /**
     * 加盐：扰乱密码
     */
    public static final String SALT="wzs";

    /**
     * 注册方法
     * @param userAccount 用户名
     * @param password 用户密码
     * @param checkCode 验证密码
     * @return 登陆成功 后续优化
     */
    @Override
    public boolean register(String userAccount , String password ,String checkCode) {
     //1、验证
        //1、非空
        if (StringUtils.isAnyEmpty(userAccount ,  password , checkCode)){
            throw new BusinessException(coedError.PARAMS_EMPTY);
        }
        //2、账户长度不得小于6
        if (userAccount.length()<6){
            throw new BusinessException(coedError.PARAMS_EMPTY,"账户长度不得小于6");
        }
        //3、密码长度不得小于6
        if (password.length()<6){
            throw new BusinessException(coedError.PARAMS_EMPTY,"密码长度不得小于6");
        }

        //4、检查账户特殊字符
        //账户由字母、数字、下划线 组成
        String regex="^[0-9a-zA-Z_]{1,}$";
        if (!userAccount.matches(regex)){
            throw new BusinessException(coedError.PARAMS_EMPTY,"账户特殊字符");
        }
        //5、是否已经存在
        QueryWrapper<UserTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        boolean exists = UTM.exists(queryWrapper);
        if (exists){
            throw new BusinessException(coedError.SYSTEM_EX,"用户已经存在");
        }
        //6、加密之后放入数据库
        String encyPassword = DigestUtils.md5DigestAsHex((SALT+password).getBytes(StandardCharsets.UTF_8));
        UserTable userTable = new UserTable();
        userTable.setUserAccount(userAccount);
        userTable.setUserPassword(encyPassword);
        int rows = UTM.insert(userTable);
        //插入失败影响行数小于 1 则失败退出
        if (rows<1){
            throw new BusinessException(coedError.SYSTEM_EX,"注册失败");
        }
        return true;
    }

    /**
     * 登陆方法
     * @param userAccount 用户名
     * @param password 用户密码
     * @param request  用于获取session
     * @return 返回user给前端展示
     */
    @Override
    public boolean doLogin(String userAccount, String password, HttpServletRequest request) {
        //1、验证
        //1、非空
        if (StringUtils.isAnyEmpty(userAccount , password )){
            throw new BusinessException(coedError.PARAMS_EMPTY);
        }
        //2、账户长度不得小于6
        if (userAccount.length()<6){
            throw new BusinessException(coedError.PARAMS_EMPTY,"账户长度不得小于6");

        }
        //3、密码长度不得小于6
        if (password.length()<6){
            throw new BusinessException(coedError.PARAMS_ERROR,"密码长度不得小于6");

        }

        //4、检查账户特殊字符
        //账户由字母、数字、下划线 组成
        String regex="^\\w+$";
        if (!userAccount.matches(regex)){
            throw new BusinessException(coedError.PARAMS_EMPTY,"账户特殊字符");

        }

        //5、是否有账户
        //加密之后密码
        String encyPassword = DigestUtils.md5DigestAsHex((SALT+password).getBytes(StandardCharsets.UTF_8));
        QueryWrapper<UserTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encyPassword);
        UserTable user = UTM.selectOne(queryWrapper);
        if (user==null){
            throw new BusinessException(coedError.SYSTEM_EX,"账号/密码错误");
        }

        UserTable safetyUser = getSafetyUser(user);


        //存放到session中
        request.getSession().setAttribute(LOGIN_STATE_CODE,safetyUser);
        return true;
    }

    /**
     * 脱敏方法
     * @param user ：用户名
     * @return ：返回脱敏后的对象
     */
    public  UserTable getSafetyUser (UserTable  user){
        //6、脱敏：就是把敏感的信息隐藏掉、返回不敏感的信息给前端展示
        UserTable userTable = new UserTable();
        userTable.setAvatarUrl(user.getAvatarUrl());
        userTable.setUserStatus(user.getUserStatus());
        userTable.setId(user.getId());
        userTable.setUsername(user.getUsername());
        userTable.setUserAccount(user.getUserAccount());
        userTable.setPhone(user.getPhone());
        userTable.setEmail(user.getEmail());
        userTable.setGender(user.getGender());
        userTable.setCreateTime(user.getCreateTime());
        userTable.setPermission(user.getPermission());
        return userTable;
    }

    /**
     * 注销方法
     * @param session :用于判断是否登陆
     */
    @Override
    public void logout(HttpSession session) {
        session.removeAttribute(LOGIN_STATE_CODE);
    }
}




