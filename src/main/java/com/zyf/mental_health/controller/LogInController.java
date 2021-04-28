package com.zyf.mental_health.controller;

import com.zyf.mental_health.data.Doctor;
import com.zyf.mental_health.data.Manager;
import com.zyf.mental_health.repositpry.DoctorRepo;
import com.zyf.mental_health.repositpry.ManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(path = "/user")
public class LogInController {
    private final ManagerRepo managerRepo;
    private final DoctorRepo doctorRepo;

    @Autowired
    public LogInController(ManagerRepo managerRepo, DoctorRepo doctorRepo) {
        this.managerRepo = managerRepo;
        this.doctorRepo = doctorRepo;
    }

    @GetMapping(path = "/login")
    public String login(Model model,
                        @RequestParam(name = "confirm", defaultValue = "true") String confirm,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        model.addAttribute("user", new Manager());
        model.addAttribute("confirm", confirm);

        return "logIn";
    }

    @PostMapping(path = "/loginUpload")
    public String loginDeal(@ModelAttribute Manager obj, Model model,HttpSession session) {
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        String id = obj.getId();
        String password = obj.getPassword();
        System.out.println(id);
        System.out.println(password);
        session.setAttribute("id",id);
        session.setAttribute("password",password);
        Manager object = managerRepo.findManagerByIdAndPassword(id, password);
        Doctor doctor = doctorRepo.findDoctorByDusernameAndDpassword(id,password);
        if (null == object) {
            if (null == doctor) {
//                model.addAttribute("confirm", "账号密码错误");
                return "redirect:/user/login?confirm=false";
            }
            session.setAttribute("doctor",doctor);
            return "redirect:/user/doctor";
        }
        return "redirect:/user/admin";
    }

    @GetMapping(path = "/admin")
    public String admin(Model model)
    {
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        model.addAttribute("doctor_name","管理员");
        return "admin";
    }

    @GetMapping(path = "/doctor")
    public String doctor(HttpSession session,Model model) {
        String id= (String) session.getAttribute("id");
        String password=(String) session.getAttribute("password");
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        Doctor doctor = doctorRepo.findDoctorByDusernameAndDpassword(id,password);
        model.addAttribute("doctor_id",doctor.getDid());
        model.addAttribute("doctor_password",doctor.getDpassword());
        model.addAttribute("doctor_name",doctor.getDname());
        model.addAttribute("doctor_research",doctor.getResearchDirection());
        model.addAttribute("doctor_age",doctor.getDage());
        model.addAttribute("doctor_title",doctor.getTitle());
        model.addAttribute("doctor_tel",doctor.getDtel());
        model.addAttribute("doctor_gender",doctor.getDgender());
        model.addAttribute("doctor_filed",doctor.getFiled());
        model.addAttribute("doctor_username",doctor.getDusername());
        session.setAttribute("doctor_id",doctor.getDid());
        session.setAttribute("doctor_name",doctor.getDname());
        session.setAttribute("doctor",doctor);
        model.addAttribute("doctor",new Doctor());
        return "doctor";
    }

    @PostMapping(path = "/checkVerifyCode")
    public void checkVerifyCode(
            HttpServletResponse response,
            HttpSession session,
            @RequestParam(name = "code") String code) {
        String verifyCode = (String) session.getAttribute("verifyCode");

        System.out.println("Code: " + code);
        System.out.println("verifyCode: " + verifyCode);

        try (PrintWriter writer = response.getWriter()) {
            if (code.equals(verifyCode)) {
                response.getWriter().write("OK");
                return;
            }

            writer.write("FAILED");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}