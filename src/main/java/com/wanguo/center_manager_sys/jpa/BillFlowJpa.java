package com.wanguo.center_manager_sys.jpa;

import com.wanguo.center_manager_sys.pojo.BillFlow;
import com.wanguo.center_manager_sys.pojo.Enterprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 描述：
 *
 * @author Badguy
 */
public interface BillFlowJpa extends JpaRepository<BillFlow, Integer>, JpaSpecificationExecutor<BillFlow> {

    Page<BillFlow> findAllByEnterprise(Enterprise enterprise, Pageable pageable);

}
