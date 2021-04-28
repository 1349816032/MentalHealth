package com.zyf.mental_health.controller;

import com.zyf.mental_health.data.Doctor;
import com.zyf.mental_health.data.MedicalRecord;
import com.zyf.mental_health.data.Patient;
import com.zyf.mental_health.repositpry.DoctorRepo;
import com.zyf.mental_health.repositpry.MedicalRecordRepo;
import com.zyf.mental_health.repositpry.PatientRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(path = "/doctor")
public class DoctorController {
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final MedicalRecordRepo medicalRecordRepo;

    @Autowired
    public DoctorController(DoctorRepo doctorRepo, PatientRepo patientRepo, MedicalRecordRepo medicalRecordRepo) {
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
        this.medicalRecordRepo = medicalRecordRepo;
    }

    @GetMapping(path = "/patientData")
    public String patientData(HttpSession session, Model model) {
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        model.addAttribute("doctor_name",doctor.getDname());
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        String pid = (String) session.getAttribute("Pid");
        String pname = (String) session.getAttribute("Pname");
        List<Patient> allPatient;
        Patient onePatient;
        model.addAttribute("patientData", new Patient());
        if ((pid == null || 0 == pid.trim().length()) && (pname == null || 0 == pname.trim().length())) {
            allPatient = (List<Patient>) patientRepo.findAll();
            model.addAttribute("allPatient", allPatient);
        } else if ((pid != null || 0 != pid.trim().length()) && (pname == null || 0 == pname.trim().length())) {
            onePatient = patientRepo.findPatientByPid(pid);
            model.addAttribute("allPatient", onePatient);
        } else if ((pid == null || 0 == pid.trim().length()) && (pname != null || 0 != pname.trim().length())) {
            allPatient = patientRepo.findPatientByPname(pname);
            model.addAttribute("allPatient", allPatient);
        } else {
            allPatient = patientRepo.findPatientByPidAndPname(pid, pname);
            model.addAttribute("allPatient", allPatient);
        }
        return "patientData";
    }

    @GetMapping(path = "/inquiry/{pid}")
    public String inquiry(@PathVariable(name = "pid") String pid,
                          HttpSession session,
                          Model model) {
        model.addAttribute("mR", new MedicalRecord());
        System.out.println(pid);
        Patient patientByPid = patientRepo.findPatientByPid(pid);
        System.out.println(patientByPid.toString());
        model.addAttribute("doctor_id", session.getAttribute("doctor_id"));
        model.addAttribute("doctor_name", session.getAttribute("doctor_name"));
        model.addAttribute("pid", patientByPid.getPid());
        model.addAttribute("pname", patientByPid.getPname());
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        return "inquiry";
    }
    @GetMapping(path = "/quiry")
    public String inquiry(Model model,HttpSession session){
        model.addAttribute("mR", new MedicalRecord());
        model.addAttribute("doctor_id",session.getAttribute("doctor_id"));
        model.addAttribute("doctor_name", session.getAttribute("doctor_name"));
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        return "inquiry1";
    }

    @PostMapping(path = "inquiryData")
    public String inquiryData(@ModelAttribute MedicalRecord mR, HttpSession session) {
        session.setAttribute("medicalrecord", mR);
        System.out.println(mR.toString());
        medicalRecordRepo.save(mR);
        String pid=mR.getPid();
        return "redirect:/doctor/inquiry/"+pid;
    }

    @PostMapping(path = "doctorchange")
    public String doctorchange(Model model, @ModelAttribute Doctor doctor, HttpSession session) {
        session.setAttribute("id", doctor.getDusername());
        session.setAttribute("password", doctor.getDpassword());
        doctorRepo.save(doctor);
        System.out.println("Doctor: " + doctor.toString());
        return "redirect:/user/doctor";
    }

    @PostMapping(path = "/search")
    public String searchByName(@ModelAttribute Patient obj, Model model, HttpSession session) {
        session.setAttribute("Pid", obj.getPid());
        session.setAttribute("Pname", obj.getPname());
        return "redirect:/doctor/patientData";
    }

}
