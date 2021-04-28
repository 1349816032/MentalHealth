package com.zyf.mental_health.repositpry;

import com.zyf.mental_health.data.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepo extends CrudRepository<Manager, String> {
    Manager findManagerByIdAndPassword(@Param("id") String id, @Param("password") String password);
}
