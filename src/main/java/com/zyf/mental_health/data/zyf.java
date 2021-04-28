package com.zyf.mental_health.data;

import com.zyf.mental_health.controller.HelloController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class zyf {
    @Autowired
    private static HelloController helloController;
    public static void main(String[] args) {
        List<UserInfo> userInfos = helloController.userInfos();
        System.out.println(userInfos);
    }
}
