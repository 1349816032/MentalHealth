package com.zyf.mental_health.data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "medical_record", schema = "mentalhealth", catalog = "")
public class MedicalRecord {
    private String patient;
    private String doctor;
    private String diagnose;
    private String record;
    private Date date;
    private String pid;
    private String did;
    private int id;

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "patient='" + patient + '\'' +
                ", doctor='" + doctor + '\'' +
                ", diagnose='" + diagnose + '\'' +
                ", record='" + record + '\'' +
                ", date=" + date +
                ", pid='" + pid + '\'' +
                ", did='" + did + '\'' +
                ", id=" + id +
                '}';
    }

    @Basic
    @Column(name = "patient")
    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    @Basic
    @Column(name = "doctor")
    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    @Basic
    @Column(name = "diagnose")
    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    @Basic
    @Column(name = "record")
    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "pid")
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "did")
    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicalRecord that = (MedicalRecord) o;

        if (id != that.id) return false;
        if (patient != null ? !patient.equals(that.patient) : that.patient != null) return false;
        if (doctor != null ? !doctor.equals(that.doctor) : that.doctor != null) return false;
        if (diagnose != null ? !diagnose.equals(that.diagnose) : that.diagnose != null) return false;
        if (record != null ? !record.equals(that.record) : that.record != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (pid != null ? !pid.equals(that.pid) : that.pid != null) return false;
        if (did != null ? !did.equals(that.did) : that.did != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = patient != null ? patient.hashCode() : 0;
        result = 31 * result + (doctor != null ? doctor.hashCode() : 0);
        result = 31 * result + (diagnose != null ? diagnose.hashCode() : 0);
        result = 31 * result + (record != null ? record.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (did != null ? did.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
