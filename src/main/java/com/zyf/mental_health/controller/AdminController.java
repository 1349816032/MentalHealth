package com.zyf.mental_health.controller;

import com.zyf.mental_health.data.Doctor;
import com.zyf.mental_health.data.Patient;
import com.zyf.mental_health.repositpry.DoctorRepo;
import com.zyf.mental_health.repositpry.PatientRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;

    public AdminController(PatientRepo patientRepo, DoctorRepo doctorRepo) {
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    @GetMapping(path = "doctorManage")
    public String doctorManage(Model model,HttpSession session){
        model.addAttribute("admin","管理员");
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        String did = (String) session.getAttribute("Did");
        String dname = (String) session.getAttribute("Dname");
        System.out.println("did:" + did);
        System.out.println("dname: " + dname);
        List<Doctor> allDocotr;
        Doctor oneDoctor;
        model.addAttribute("doctorData", new Doctor());
        if ((did == null || 0 == did.trim().length()) && (dname == null || 0 == dname.trim().length())) {
            allDocotr = (List<Doctor>) doctorRepo.findAll();
            model.addAttribute("allDoctor", allDocotr);
            System.out.println("allDoctor1:" + allDocotr.toString());

        } else if ((did != null || 0 != did.trim().length()) && (dname == null || 0 == dname.trim().length())) {
            oneDoctor = doctorRepo.findDoctorByDid(did);
            model.addAttribute("allDoctor", oneDoctor);
            System.out.println("oneDoctor2:" + oneDoctor.toString());
        } else if ((did == null || 0 == did.trim().length()) && (dname != null || 0 != dname.trim().length())) {
            allDocotr = doctorRepo.findDoctorByDname(dname);
            model.addAttribute("allDoctor", allDocotr);
            System.out.println("allDoctor3:" + allDocotr.toString());

        } else {
            allDocotr = doctorRepo.findDoctorByDidAndDname(did,dname);
            System.out.println("allDoctor4:" + allDocotr.toString());
            model.addAttribute("allDoctor", allDocotr);
        }
        return "doctorManage";
    }
    @GetMapping(path = "patientManage")
    public String userManage(Model model, HttpSession session){
        model.addAttribute("admin","管理员");
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
        return "patientManage";
    }
    @GetMapping(path = "systemManage")
    public String systemManage(Model model){
        model.addAttribute("admin","管理员");
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        return "systemManage";
    }


    @GetMapping(path = "/patientDetailManage/{pid}")
    public String patientDetailManage(Model model, @PathVariable(name = "pid") String pid){
        model.addAttribute("admin","管理员");
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        model.addAttribute("patientDetail",new Patient());
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
        return "patientDetailMange";
    }
    @PostMapping(path = "/patientChange")
    public String patientChange(HttpSession session,
                                @ModelAttribute Patient patient,
                                Model model) {
        model.addAttribute("admin","管理员");
        System.out.println("Patient: " + patient.toString());
        patientRepo.save(patient);
        String pid= (String) session.getAttribute("patientid");
        System.out.println("pid :"+pid);
        return "redirect:/admin/patientDetailManage/"+patient.getPid();

//        return "redirect:addPatientData";
    }

    @PostMapping(path = "/searchPatient")
    public String searchPatient(@ModelAttribute Patient obj, Model model, HttpSession session) {
        session.setAttribute("Pid", obj.getPid());
        session.setAttribute("Pname", obj.getPname());
        return "redirect:/admin/patientManage";
    }

    @PostMapping(path = "/searchDoctor")
    public String searchDoctor(@ModelAttribute Doctor obj, Model model, HttpSession session) {
        session.setAttribute("Did", obj.getDid());
        session.setAttribute("Dname", obj.getDname());
        return "redirect:/admin/doctorManage";
    }

    @GetMapping(path = "/doctorDetail/{did}")
    public String doctorDetail(@PathVariable(name = "did") String did,Model model,HttpSession session){
        model.addAttribute("admin","管理员");
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        Doctor doctor=doctorRepo.findDoctorByDid(did);
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
        return "doctorDetail";
    }

    @PostMapping(path = "/doctorchange")
    public String doctorchange(Model model, @ModelAttribute Doctor doctor, HttpSession session) {
        String did=doctor.getDid();
        doctorRepo.save(doctor);
        System.out.println("Doctor: " + doctor.toString());
        return "redirect:/admin/doctorDetail/"+did;
    }

    @PostMapping(path = "/addDoctorChange")
    public String addDoctorChange(@ModelAttribute Doctor doctor){
        doctorRepo.save(doctor);
        return "redirect:/admin/doctorDetail/"+doctor.getDid();
    }

    @GetMapping(path = "/addDoctor")
    public String addDoctor(Model model){
        model.addAttribute("doctor",new Doctor());
        model.addAttribute("admin","管理员");
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        return "addDoctor";
    }

    @GetMapping(path = "adminAddPatient")
    public String adminAddPatient(Model model){
        model.addAttribute("admin","管理员");
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        model.addAttribute("patient",new Patient());
        return "adminAddPatient";
    }

    @PostMapping(path = "/change")
    public String change(@ModelAttribute Patient patient){
        System.out.println("one test:"+patient.toString());
        patientRepo.save(patient);
        return "redirect:/admin/patientDetailManage/"+patient.getPid();
    }

}
