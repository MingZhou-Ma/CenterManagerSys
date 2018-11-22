package com.wanguo.center_manager_sys.jpa;

import com.wanguo.center_manager_sys.pojo.BillFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 描述：
 *
 * @author Badguy
 */
@Repository
public interface BillFlowJpa extends JpaRepository<BillFlow, Integer>, JpaSpecificationExecutor<BillFlow> {

}
