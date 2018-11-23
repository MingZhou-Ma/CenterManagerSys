package com.wanguo.center_manager_sys.jpa;

import com.wanguo.center_manager_sys.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 描述：
 *
 * @author Badguy
 */
public interface AdminJpa extends JpaRepository<Admin, Integer>, JpaSpecificationExecutor<Admin> {

    Admin findByUsernameAndPassword(String username, String password);
}
