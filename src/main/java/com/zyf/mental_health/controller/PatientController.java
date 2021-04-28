package com.zyf.mental_health.controller;

import com.zyf.mental_health.data.Doctor;
import com.zyf.mental_health.data.MedicalRecord;
import com.zyf.mental_health.data.Patient;
import com.zyf.mental_health.repositpry.MedicalRecordRepo;
import com.zyf.mental_health.repositpry.PatientRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/patient")
public class PatientController {
    private final PatientRepo patientRepo;
    private final MedicalRecordRepo medicalRecordRepo;
    public PatientController(PatientRepo patientRepo, MedicalRecordRepo medicalRecordRepo) {
        this.patientRepo = patientRepo;
        this.medicalRecordRepo = medicalRecordRepo;
    }
    @GetMapping("addPatientData")
    public String addpatientData(Model model,HttpSession session){
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        model.addAttribute("doctor_name",doctor.getDname());
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        model.addAttribute("patientDetail",new Patient());
        return "addPatientData";
    }
    @GetMapping(path = "/patientDetail/{pid}")
    public String patientDetail(@PathVariable(name = "pid") String pid,
                                Model model,
                                HttpSession session){
        System.out.println("pid: " + pid);
        model.addAttribute("patientDetail",new Patient());
        session.setAttribute("patientid",pid);
        Patient patient = patientRepo.findPatientByPid(pid);
        System.out.println("Patient: " + patient.toString());
        model.addAttribute("id",patient.getId());
        model.addAttribute("pid",patient.getPid());
        model.addAttribute("pname",patient.getPname());
        model.addAttribute("page",patient.getPage());
        model.addAttribute("pgender",patient.getPgender());
        model.addAttribute("diagnose",patient.getDiagnosis());
        model.addAttribute("ptel",patient.getPtel());
        model.addAttribute("time",patient.getTime());
        model.addAttribute("occupation",patient.getOccupation());
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        model.addAttribute("doctor_name",doctor.getDname());
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        return "patientDetail";
    }
    @PostMapping(path = "/change")
    public String change(@ModelAttribute Patient patient){
        System.out.println("one test:"+patient.toString());
        patientRepo.save(patient);
        return "redirect:/patient/patientDetail/"+patient.getPid();
    }
    @PostMapping(path = "/patientChange")
    public String patientChange(HttpSession session,
                                @ModelAttribute Patient patient,
                                Model model) {
        System.out.println("Patient: " + patient.toString());
        patientRepo.save(patient);
        String pid= (String) session.getAttribute("patientid");
        System.out.println("pid :"+pid);
        return "redirect:/patient/patientDetail/"+patient.getPid();

//        return "redirect:addPatientData";
    }

    @GetMapping("medicalRecord/{pid}")
    public String medicalRecord(@PathVariable(name = "pid") String pid,
                                Model model,HttpSession session){
        List<MedicalRecord> medicalRecord = medicalRecordRepo.findMedicalRecordByPid(pid);
        for(MedicalRecord i:medicalRecord){
            System.out.println(i.toString());
        }
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        model.addAttribute("doctor_name",doctor.getDname());
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        model.addAttribute("medicalRecord",medicalRecord);
        model.addAttribute("patientData", new Patient());
        return "medicalRecord";
    }
    @GetMapping("medicalRecordDetail/{id}")
    public String medicalRecordDetail(@PathVariable(name="id") int id,Model model,HttpSession session){
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        model.addAttribute("doctor_name",doctor.getDname());
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        MedicalRecord medicalRecordById = medicalRecordRepo.findMedicalRecordById(id);
        model.addAttribute("medicalRecordDetail",medicalRecordById);
        return "medicalRecordDetail";
    }
}
