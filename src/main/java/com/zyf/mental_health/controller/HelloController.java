package com.zyf.mental_health.controller;

import com.zyf.mental_health.data.UserInfo;
import com.zyf.mental_health.mapper.UserInfoMapper;
import com.zyf.mental_health.repositpry.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "")
public class HelloController {
    private final JdbcTemplate jdbcTemplate;

    private final UserInfoRepo userInfoRepo;

    @Autowired
    public HelloController(UserInfoRepo userInfoRepo,
                           JdbcTemplate jdbcTemplate) {
        this.userInfoRepo = userInfoRepo;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping(path = "/hello")
    public String hello(Model model) {
        model.addAttribute("user", "LiuXianghai");

        return "hello";
    }
    @GetMapping(path = "/index")
    public String index(Model model){
        return "admin";
    }
    @GetMapping(path = "/hello1")
    public String hello1(Model model) {
        model.addAttribute("user", "ZhaoYongfeng");

        return "hello";
    }

    @GetMapping(path = "/saveUserInfo")
    @ResponseBody
    public HttpStatus saveUserInfo(
            @RequestParam(name = "userId") String userId,
            @RequestParam(name = "userName") String userName,
            @RequestParam(name = "userPassword") String password,
            @RequestBody UserInfo user
    ){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(Long.parseLong(userId));
        userInfo.setUserName(userName);
        userInfo.setUserPassword(password);

        userInfoRepo.save(userInfo);

        return HttpStatus.OK;
    }

//    @GetMapping(path = "/LogIn")
//    public String form(Model model) {
//        model.addAttribute("userInfo", new UserInfo());
//
//        return "logIn";
//    }
//
//    @PostMapping(path = "/LogIn")
//    @ResponseBody
//    public HttpStatus postForm(@ModelAttribute UserInfo userInfo) {
//        int flag=0;
//        List<UserInfo> userInfos = helloController.userInfos();
//        System.out.println("UserInfo: " + userInfo.toString());
//        for(UserInfo user:userInfos){
//            if(user.getUserPassword().equals(userInfo.getUserPassword())&&user.getUserName().equals(userInfo.getUserName())&&user.getUserId()==userInfo.getUserId()){
//                flag=1;
//            }
//        }
//        if(flag==1) return HttpStatus.OK;
//        else
//            return HttpStatus.ACCEPTED;
//    }


    @ResponseBody
    @GetMapping(path = "/userInfo")
    public List<UserInfo> userInfos(){
        List<UserInfo> userInfos = jdbcTemplate.query("SELECT * FROM user_info", new UserInfoMapper());

        return userInfos;
    }
}
