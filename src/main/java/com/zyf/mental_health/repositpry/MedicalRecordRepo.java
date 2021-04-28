package com.zyf.mental_health.repositpry;

import com.zyf.mental_health.data.MedicalRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepo extends CrudRepository<MedicalRecord,String> {
    List<MedicalRecord> findMedicalRecordByPid(@Param("patient") String patient);
    MedicalRecord findMedicalRecordById(@Param("id") int Id);
}
