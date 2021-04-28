package com.zyf.mental_health.data;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Doctor {
    private String dusername;
    private String did;
    private String dpassword;
    private String dname;
    private int dage;
    private String dgender;
    private String researchDirection;
    private String dtel;
    private String filed;
    private String title;

    @Id
    @Column(name = "dusername")
    public String getDusername() {
        return dusername;
    }

    public void setDusername(String dusername) {
        this.dusername = dusername;
    }

    @Basic
    @Column(name = "did")
    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    @Basic
    @Column(name = "dpassword")
    public String getDpassword() {
        return dpassword;
    }

    public void setDpassword(String dpassword) {
        this.dpassword = dpassword;
    }

    @Basic
    @Column(name = "dname")
    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Basic
    @Column(name = "dage")
    public int getDage() {
        return dage;
    }

    public void setDage(int dage) {
        this.dage = dage;
    }

    @Basic
    @Column(name = "dgender")
    public String getDgender() {
        return dgender;
    }

    public void setDgender(String dgender) {
        this.dgender = dgender;
    }

    @Basic
    @Column(name = "research_direction")
    public String getResearchDirection() {
        return researchDirection;
    }

    public void setResearchDirection(String researchDirection) {
        this.researchDirection = researchDirection;
    }

    @Basic
    @Column(name = "dtel")
    public String getDtel() {
        return dtel;
    }

    public void setDtel(String dtel) {
        this.dtel = dtel;
    }

    @Basic
    @Column(name = "filed")
    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        if (dage != doctor.dage) return false;
        if (dusername != null ? !dusername.equals(doctor.dusername) : doctor.dusername != null) return false;
        if (did != null ? !did.equals(doctor.did) : doctor.did != null) return false;
        if (dpassword != null ? !dpassword.equals(doctor.dpassword) : doctor.dpassword != null) return false;
        if (dname != null ? !dname.equals(doctor.dname) : doctor.dname != null) return false;
        if (dgender != null ? !dgender.equals(doctor.dgender) : doctor.dgender != null) return false;
        if (researchDirection != null ? !researchDirection.equals(doctor.researchDirection) : doctor.researchDirection != null)
            return false;
        if (dtel != null ? !dtel.equals(doctor.dtel) : doctor.dtel != null) return false;
        if (filed != null ? !filed.equals(doctor.filed) : doctor.filed != null) return false;
        if (title != null ? !title.equals(doctor.title) : doctor.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dusername != null ? dusername.hashCode() : 0;
        result = 31 * result + (did != null ? did.hashCode() : 0);
        result = 31 * result + (dpassword != null ? dpassword.hashCode() : 0);
        result = 31 * result + (dname != null ? dname.hashCode() : 0);
        result = 31 * result + dage;
        result = 31 * result + (dgender != null ? dgender.hashCode() : 0);
        result = 31 * result + (researchDirection != null ? researchDirection.hashCode() : 0);
        result = 31 * result + (dtel != null ? dtel.hashCode() : 0);
        result = 31 * result + (filed != null ? filed.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
