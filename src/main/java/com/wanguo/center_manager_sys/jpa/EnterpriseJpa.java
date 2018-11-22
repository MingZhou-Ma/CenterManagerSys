package com.wanguo.center_manager_sys.jpa;

import com.wanguo.center_manager_sys.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 描述：
 *
 * @author Badguy
 */
@Repository
public interface EnterpriseJpa extends JpaRepository<Enterprise, Integer>, JpaSpecificationExecutor<Enterprise> {

    Enterprise findByAppId(String appId);

}
