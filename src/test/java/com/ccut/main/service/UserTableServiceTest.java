//package com.ccut.main.service;
//
//import com.ccut.main.bean.UserTable;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//@SpringBootTest
//class UserTableServiceTest {
//    @Autowired
//    UserTableService  UTS;
//
//    @Test
//    public void  addUser(){
//        UserTable userTable = new UserTable();
//        userTable.setAvatarUrl("%dsadhadak%");
//        userTable.setUsername("meSHI帅哥");
//        userTable.setUserAccount("789666");
//        userTable.setUserPassword("032700");
//        userTable.setPhone("15590091502");
//        userTable.setEmail("1835187730@qq.com");
//        userTable.setGender(1);
//        boolean save = UTS.save(userTable);
////        Assertions.assertTrue(save);
//    }
//
//
//    @Test
//    void register() {
//        //测试 非空
//        String userAccount1="";
//        String password1="null";
//        String checkCode1="123456";
//        boolean register1 = UTS.register(userAccount1, password1, checkCode1);
//        Assertions.assertEquals(register1,-1L);
//        //测试  账户小于6
//        String userAccount2="user";
//        String password2="1234122";
//        String checkCode2="1234122";
//        boolean register2 = UTS.register(userAccount2, password2, checkCode2);
//        Assertions.assertEquals(register2,-2L);
//        //测试  密码小于6
//        String userAccount3="userCoreRegisterTest";
//        String password3="1234";
//        String checkCode3="123456";
//        boolean register3 = UTS.register(userAccount3, password3, checkCode3);
//        Assertions.assertEquals(register3,-3L);
//        //测试  特殊字符
//        String userAccount4="userC%%oreRegisterTest";
//        String password4="123456";
//        String checkCode4="123456";
//        boolean register4 = UTS.register(userAccount4, password4, checkCode4);
//        Assertions.assertEquals(register4,-4L);
//        //测试  已经存在
//        String userAccount="userCoreRegisterTest";
//        String password="123456";
//        String checkCode="123456";
//        boolean register = UTS.register(userAccount, password, checkCode);
//        Assertions.assertEquals(register,-5L);
//        //测试  成功加入
//        String userAccount6="userCoreRegisterTest2";
//        String password6="123456";
//        String checkCode6="123456";
//        boolean register6 = UTS.register(userAccount6, password6, checkCode6);
//        Assertions.assertEquals(register6,666L);
//
//    }
//}