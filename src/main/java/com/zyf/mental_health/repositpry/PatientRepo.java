package com.zyf.mental_health.repositpry;

import com.zyf.mental_health.data.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepo extends CrudRepository<Patient,String> {
    @Query(value="SELECT * FROM patient WHERE pid=:pid", nativeQuery=true)
    Patient findPatientByPid(@Param("pid") String pid);
    List<Patient> findPatientByPname(@Param("pname") String pname);
    List<Patient>findPatientByPidAndPname(@Param("pid") String pid,@Param("pname") String pname);
}
