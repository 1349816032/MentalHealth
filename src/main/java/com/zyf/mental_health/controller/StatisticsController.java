package com.zyf.mental_health.controller;

import com.zyf.mental_health.data.Doctor;
import com.zyf.mental_health.data.Patient;
import com.zyf.mental_health.repositpry.DoctorRepo;
import com.zyf.mental_health.repositpry.MedicalRecordRepo;
import com.zyf.mental_health.repositpry.PatientRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(path = "Data")
public class StatisticsController {
    private final PatientRepo patientRepo;
    private final MedicalRecordRepo medicalRecordRepo;
    private final DoctorRepo doctorRepo;
    public StatisticsController(PatientRepo patientRepo, MedicalRecordRepo medicalRecordRepo, DoctorRepo doctorRepo) {
        this.patientRepo = patientRepo;
        this.medicalRecordRepo = medicalRecordRepo;
        this.doctorRepo = doctorRepo;
    }

    @GetMapping("/statistics")
    public String statistics(Model model , HttpSession session){
        model.addAttribute("doctor_name","管理员");
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        return "statistics";
    }

    @GetMapping("occupationRate")
    @ResponseBody
    public Map<String, ArrayList<String>> occupation(){
        Map<String,Integer> m=new HashMap<>();
        Map<String ,ArrayList<String>> map=new HashMap<>();
        ArrayList<String> data1=new ArrayList<>();
        ArrayList<String> data2=new ArrayList<>();
        List<Patient> all = (List<Patient>) patientRepo.findAll();
        for(Patient i : all){
            if(m.containsKey(i.getOccupation())){
                Integer value=m.get(i.getOccupation());
                int j=value;
                j++;
                m.put(i.getOccupation(),j);
            }
            else{
                m.put(i.getOccupation(),1);
            }
        }
        int i=0;
        for(String key : m.keySet()){
            data1.add(i,String.valueOf(m.get(key)));
            data2.add(i,key);
            i++;
        }
        map.put("1",data1);
        map.put("2",data2);
        return map;
    }

    @GetMapping("genderRate")
    @ResponseBody
    public Map<String, String> Data(){
        Map<String,String> map=new HashMap<>();
        Iterable<Patient> all = patientRepo.findAll();
        int male=0,female=0;
        for(Patient i: all){
            if(i.getPgender().equals("male")) male++;
            else female++;
        }
        map.put("male",String.valueOf(male));
        map.put("female",String.valueOf(female));
        map.put("legend","人数");
        map.put("title","病人性别分布");
        return map;
    }

    @GetMapping("doctorGender")
    @ResponseBody
    public Map<String, String> doctorData(){
        Map<String,String> map=new HashMap<>();
        Iterable<Doctor> all = doctorRepo.findAll();
        int male=0,female=0;
        for(Doctor i: all){
            if(i.getDgender().equals("male")) male++;
            else female++;
        }
        map.put("male",String.valueOf(male));
        map.put("female",String.valueOf(female));
        map.put("legend","人数");
        map.put("title","医生性别分布");
        return map;
    }

    @GetMapping("jiuzhenRate")
    @ResponseBody
    public Map<String,String> jiuzhenRate(){
        Map<String,String> map=new HashMap<>();
        int[] count=new int[10];
        List<Patient> all = (List<Patient>) patientRepo.findAll();
        for(Patient i:all){
            switch (medicalRecordRepo.findMedicalRecordByPid(i.getPid()).size())
            {
                case 1: count[0]++; break;
                case 2: count[1]++; break;
                case 3: count[2]++; break;
                case 4: count[3]++; break;
                case 5: count[4]++; break;
                case 6: count[5]++; break;
                case 7: count[6]++; break;
                case 8: count[7]++; break;
                case 9: count[8]++; break;
                case 10: count[9]++; break;
                default:break;
            }
        }
        for(int i=0;i<count.length;i++){
            map.put("就诊"+(i+1)+"次患者",String.valueOf(count[i]));
        }
        return map;
    }

    @GetMapping("ageRate")
    @ResponseBody
    public int[] ageRate(){
        int[] data=new int[7];
        List<Patient> all = (List<Patient>) patientRepo.findAll();
        for(Patient patient:all){
            if(patient.getPage()>0&&patient.getPage()<=18) data[0]++;
            if(patient.getPage()>18&&patient.getPage()<=25) data[1]++;
            if(patient.getPage()>25&&patient.getPage()<=32) data[2]++;
            if(patient.getPage()>32&&patient.getPage()<=40) data[3]++;
            if(patient.getPage()>40&&patient.getPage()<=48) data[4]++;
            if(patient.getPage()>48&&patient.getPage()<=60) data[5]++;
            if(patient.getPage()>60) data[6]++;
        }
        return data;
    }

    @GetMapping("doctorAge")
    @ResponseBody
    public int[] doctorAge() {
        int[] data = new int[7];
        List<Doctor> all = (List<Doctor>) doctorRepo.findAll();
        for (Doctor doctor : all) {
            if (doctor.getDage() > 0 && doctor.getDage() <= 18) data[0]++;
            if (doctor.getDage() > 18 && doctor.getDage() <= 25) data[1]++;
            if (doctor.getDage() > 25 && doctor.getDage() <= 32) data[2]++;
            if (doctor.getDage() > 32 && doctor.getDage() <= 40) data[3]++;
            if (doctor.getDage() > 40 && doctor.getDage() <= 48) data[4]++;
            if (doctor.getDage() > 48 && doctor.getDage() <= 60) data[5]++;
            if (doctor.getDage() > 60) data[6]++;
        }
        return data;
    }

    @GetMapping("patientStatic/{pid}")
    public String patientStatic(Model model, @PathVariable(name="pid") String pid, HttpSession session){
        model.addAttribute("patient",patientRepo.findPatientByPid(pid));
        model.addAttribute("count",medicalRecordRepo.findMedicalRecordByPid(pid).size());
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        model.addAttribute("doctor_name",doctor.getDname());
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        model.addAttribute("Date",sdf.format(date));
        return "patientStatic";
    }
}
