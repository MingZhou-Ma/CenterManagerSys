package com.wanguo.center_manager_sys.service;

import com.alibaba.fastjson.JSONObject;
import com.wanguo.center_manager_sys.pojo.Enterprise;
import com.wanguo.center_manager_sys.utils.ResJson;

/**
 * 描述：
 *
 * @author Badguy
 */
public interface EnterpriseService {

    //ResJson addEnterprise(String token, Enterprise enterprise);
    ResJson addEnterprise(String token, String appId, String name);

    ResJson delEnterprise(String token, Integer id);

    ResJson findEnterpriseList(String token, Integer page, Integer size);

    ResJson findBillFlowListByEnterprise(String token, Integer page, Integer size, Integer id);

    ResJson updateNumOfNewCustomer(JSONObject jsonObject);

    ResJson getEnterpriseByAppId(JSONObject jsonObject);
}
