package com.zyf.mental_health.repositpry;

import com.zyf.mental_health.data.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepo extends CrudRepository<UserInfo, Long> {
}
