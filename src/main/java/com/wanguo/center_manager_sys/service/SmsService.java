package com.wanguo.center_manager_sys.service;

import com.alibaba.fastjson.JSONObject;
import com.wanguo.center_manager_sys.pojo.Admin;
import com.wanguo.center_manager_sys.utils.ResJson;

/**
 * 描述：
 *
 * @author Badguy
 */
public interface SmsService {

    ResJson groupSms(JSONObject jsonObject);
}
