package com.ccut.main.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccut.main.Exception.BusinessException;
import com.ccut.main.bean.UserTable;
import com.ccut.main.common.BaseResponse;
import com.ccut.main.common.coedError;
import com.ccut.main.common.responseUtils;
import com.ccut.main.model.request.userLoginRequest;
import com.ccut.main.model.request.userRegisterRequest;
import com.ccut.main.service.UserTableService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

import static com.ccut.main.content.userPermission.LOGIN_STATE_CODE;
import static com.ccut.main.content.userPermission.MANAGE_USER;

/**
 * @author wz
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserTableService UTS;


    /**
     * 返回当前用户信息
     *
     * @param session ：获取user对象
     * @return ：返回当前用户对象
     */
    @GetMapping("/current")
    public BaseResponse<UserTable> test(HttpSession session){
        Object attribute = session.getAttribute(LOGIN_STATE_CODE);
        UserTable  user = (UserTable)attribute;
        if (user==null){
            throw new BusinessException(coedError.NOT_LOGIN);
        }
        Integer id = user.getId();
        //查库获得的user
        UserTable realityUser = UTS.getById(id);
        //脱敏后的user
        UserTable safetyUser = UTS.getSafetyUser(realityUser);

        return responseUtils.success(safetyUser);

    }
    @GetMapping("hh")
    public String GET(){

        return "你好";
    }

    /**
     * 注册方法
     *
     * @param userRegisterRequest ：封装的pojo对应前端json
     * @return ：返回注册信息
     */
    @PostMapping("/register")
    public BaseResponse<Boolean> register(@RequestBody userRegisterRequest userRegisterRequest){
        //controller的简单验证
        if (userRegisterRequest==null){
            throw new BusinessException(coedError.PARAMS_EMPTY);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String checkCode = userRegisterRequest.getCheckCode();
        String password = userRegisterRequest.getPassword();
        if (StringUtils.isAllBlank(userAccount,checkCode,password)){
            throw new BusinessException(coedError.PARAMS_EMPTY,"账号密码为空");
        }
        Boolean register = UTS.register(userAccount, password, checkCode);

      return  responseUtils.success(register);
    }

    /**
     * 登陆方法
     *
     * @param userLoginRequest ：封装的pojo对应前端json
     * @param request          ：用于传参获取session
     * @return ：返回登陆信息给前端展示
     */
    @PostMapping("/login")
    public BaseResponse<Boolean> login(@RequestBody userLoginRequest userLoginRequest, HttpServletRequest request){
        //controller的简单验证
        if (userLoginRequest==null){
            throw new BusinessException(coedError.PARAMS_EMPTY);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String password = userLoginRequest.getPassword();
        if (StringUtils.isAllBlank(password,userAccount)){
            throw new BusinessException(coedError.PARAMS_EMPTY,"账号密码为空");
        }
        boolean loginMes = UTS.doLogin(userAccount, password, request);
        return  responseUtils.success(loginMes);
    }

    /**
     * 查找功能 且只有管理员能使用
     *
     * @param username :用户名
     * @param request  ：用于获取session
     * @return List<UserTable>
     */
    @GetMapping("/select")
    public BaseResponse<List<UserTable>> select(String username, HttpServletRequest request){
        //如果不是管理员则删除失败
        if (!isAdmin(request)){
            throw new BusinessException(coedError.NOT_PERMISSION,"不是管理员身份");
        }
        QueryWrapper<UserTable> userTableQueryWrapper = new QueryWrapper<>();
        if (username!=null){
            userTableQueryWrapper.like("username",username);
        }

        List<UserTable> list = UTS.list(userTableQueryWrapper);
        return responseUtils.success(list);
    }


    /**
     * 删除方法 切只有管理员能进行该功能
     * @param id 通过id删除
     * @param request ：用于获取session
     * @return ：返回山粗结果
     */
    @DeleteMapping("/delete")
    public BaseResponse<Boolean> delete(int id, HttpServletRequest request){
        //如果不是管理员则删除失败
        if (isAdmin(request)){
            throw new BusinessException(coedError.NOT_PERMISSION,"不是管理员身份");
        }

        QueryWrapper<UserTable> userTableQueryWrapper = new QueryWrapper<>();
        if (id<0){
            throw new BusinessException(coedError.SYSTEM_EX,"用户存在");
        }
        userTableQueryWrapper.like("id",id);
        boolean remove = UTS.remove(userTableQueryWrapper);
        return responseUtils.success(remove);
    }

    @PostMapping("/logout")
    public void logout(HttpSession session) {
        if (session==null){
            throw new BusinessException(coedError.SYSTEM_EX,"未登录");
        }
        UTS.logout(session);
    }

    /**
     * 判断权限
     * @param request ：用于获取session
     * @return 返回判断结果
     */
    public boolean isAdmin(HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(LOGIN_STATE_CODE);
        if (attribute==null){
            throw new BusinessException(coedError.SYSTEM_EX,"未登录");
        }
        UserTable user=(UserTable)attribute;
        return Objects.equals(user.getPermission(), MANAGE_USER);
    }
}


















