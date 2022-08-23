package com.example.usercore;

import com.ccut.main.UserCoreApplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@SpringBootTest(classes = {UserCoreApplication.class})
class UserCoreApplicationTests {



    @Test
    void contextLoads() {
        System.out.println(LocalDate.now().lengthOfYear());

        String s = DigestUtils.md5DigestAsHex("123".getBytes(StandardCharsets.UTF_8));
        System.out.println(s);
    }

    /**
     * 测试正则
     */
    @Test
    void regex() {
        String regex="^\\w{3,20}$";
//        Assertions.assertTrue("%23".matches(regex));
        Assertions.assertTrue("123AAA_21321".matches(regex));
    }

}
