package com.wanguo.center_manager_sys.task;

import com.wanguo.center_manager_sys.jpa.BillFlowJpa;
import com.wanguo.center_manager_sys.jpa.EnterpriseJpa;
import com.wanguo.center_manager_sys.pojo.BillFlow;
import com.wanguo.center_manager_sys.pojo.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 描述：
 *
 * @author Badguy
 */
@Component
public class BillClear {

    private final EnterpriseJpa enterpriseJpa;

    private final BillFlowJpa billFlowJpa;

    @Autowired
    public BillClear(EnterpriseJpa enterpriseJpa, BillFlowJpa billFlowJpa) {
        this.enterpriseJpa = enterpriseJpa;
        this.billFlowJpa = billFlowJpa;
    }

    /**
     * 账单清零
     */
    @Scheduled(cron = "0 0 0 1 * ?")
    public void job() {
        List<Enterprise> list = enterpriseJpa.findAll();
        if (null != list && !list.isEmpty()) {
            for (Enterprise enterprise : list) {
                BillFlow billFlow = new BillFlow();
                //billFlow.setSmsFee(enterprise.getSmsFee());
                //billFlow.setNumOfNewCustomer(enterprise.getNumOfNewCustomer());
                billFlow.setTotalFee(enterprise.getSmsFee() + enterprise.getNumOfNewCustomer() * 0.8);
                billFlow.setTime(new Date());
                billFlow.setEnterprise(enterprise);
                billFlowJpa.save(billFlow);

                enterprise.setSmsFee(0D);
                enterprise.setNumOfNewCustomer(0L);
                enterpriseJpa.save(enterprise);
            }
        }
    }
}
