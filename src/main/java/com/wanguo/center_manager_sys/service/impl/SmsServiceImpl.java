package com.wanguo.center_manager_sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wanguo.center_manager_sys.jpa.EnterpriseJpa;
import com.wanguo.center_manager_sys.pojo.Enterprise;
import com.wanguo.center_manager_sys.service.SmsService;
import com.wanguo.center_manager_sys.utils.GroupSmsUtil;
import com.wanguo.center_manager_sys.utils.ResJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：
 *
 * @author Badguy
 */
@Service
public class SmsServiceImpl implements SmsService {

    private final EnterpriseJpa enterpriseJpa;

    @Autowired
    public SmsServiceImpl(EnterpriseJpa enterpriseJpa) {
        this.enterpriseJpa = enterpriseJpa;
    }

    @Override
    public ResJson groupSms(JSONObject jsonObject) {
        String phone = jsonObject.getString("phone");
        String signName = jsonObject.getString("signName");
        String param = jsonObject.getString("param");
        String appId = jsonObject.getString("appId");
        Integer numbers = jsonObject.getInteger("numbers");
        Enterprise enterprise = enterpriseJpa.findByAppId(appId);
        if (null == enterprise) {
            return ResJson.failJson(4000, "企业不存在", null);
        }
        if (!GroupSmsUtil.groupSms(phone, signName, param)) {
            // 发送失败
            return ResJson.failJson(4000, "发送短信失败", null);
        }
        enterprise.setSmsFee(enterprise.getSmsFee() + numbers * 0.08);
        //enterprise.setTotalFee(enterprise.getSmsFee() + enterprise.getNumOfNewCustomer() * 0.8);
        enterpriseJpa.save(enterprise);
        return ResJson.successJson("group sms success");
    }
}
