package com.zyf.mental_health;

import com.zyf.mental_health.controller.HelloController;
import com.zyf.mental_health.data.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MentalHealthApplicationTests {
    @Autowired
    private HelloController helloController;
    @Test
    void contextLoads() {
        List<UserInfo> userInfos = helloController.userInfos();
        System.out.println(userInfos);
    }

}
