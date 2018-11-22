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

    ResJson addEnterprise(String token, Enterprise enterprise);

    ResJson findEnterpriseList(String token, Integer page, Integer size);
    //ResJson findEnterpriseList(JSONObject j);

    ResJson updateNumOfNewCustomer(JSONObject jsonObject);
}
