package com.zyf.mental_health.data;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "patient", schema = "mentalhealth", catalog = "")
public class Patient {
    private int id;
    private String pid;
    private String pname;
    private int page;
    private String pgender;
    private String diagnosis;
    private String ptel;
    private Date time;
    private String occupation;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    @Column(name = "pname")
    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Basic
    @Column(name = "page")
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Basic
    @Column(name = "pgender")
    public String getPgender() {
        return pgender;
    }

    public void setPgender(String pgender) {
        this.pgender = pgender;
    }

    @Basic
    @Column(name = "diagnosis")
    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Basic
    @Column(name = "ptel")
    public String getPtel() {
        return ptel;
    }

    public void setPtel(String ptel) {
        this.ptel = ptel;
    }

    @Basic
    @Column(name = "time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column(name = "occupation")
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        if (id != patient.id) return false;
        if (page != patient.page) return false;
        if (pid != null ? !pid.equals(patient.pid) : patient.pid != null) return false;
        if (pname != null ? !pname.equals(patient.pname) : patient.pname != null) return false;
        if (pgender != null ? !pgender.equals(patient.pgender) : patient.pgender != null) return false;
        if (diagnosis != null ? !diagnosis.equals(patient.diagnosis) : patient.diagnosis != null) return false;
        if (ptel != null ? !ptel.equals(patient.ptel) : patient.ptel != null) return false;
        if (time != null ? !time.equals(patient.time) : patient.time != null) return false;
        if (occupation != null ? !occupation.equals(patient.occupation) : patient.occupation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (pname != null ? pname.hashCode() : 0);
        result = 31 * result + page;
        result = 31 * result + (pgender != null ? pgender.hashCode() : 0);
        result = 31 * result + (diagnosis != null ? diagnosis.hashCode() : 0);
        result = 31 * result + (ptel != null ? ptel.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (occupation != null ? occupation.hashCode() : 0);
        return result;
    }
}
