package com.zyf.mental_health.repositpry;

import com.zyf.mental_health.data.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepo extends CrudRepository<Doctor,String> {
    Doctor findDoctorByDusernameAndDpassword(@Param("id") String id, @Param("password") String password);
    Doctor findDoctorByDid(@Param("did") String Did);
    List<Doctor> findDoctorByDname(@Param("dname") String Dname);
    List<Doctor> findDoctorByDidAndDname(@Param("did") String did,@Param("dname") String dname);

}
