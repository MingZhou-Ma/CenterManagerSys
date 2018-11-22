package com.wanguo.center_manager_sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wanguo.center_manager_sys.jpa.EnterpriseJpa;
import com.wanguo.center_manager_sys.pojo.Enterprise;
import com.wanguo.center_manager_sys.service.EnterpriseService;
import com.wanguo.center_manager_sys.service.TokenService;
import com.wanguo.center_manager_sys.utils.ResJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 描述：
 *
 * @author Badguy
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    private final TokenService tokenService;

    private final EnterpriseJpa enterpriseJpa;

    @Autowired
    public EnterpriseServiceImpl(EnterpriseJpa enterpriseJpa, TokenService tokenService) {
        this.enterpriseJpa = enterpriseJpa;
        this.tokenService = tokenService;
    }

    @Override
    public ResJson addEnterprise(String token, Enterprise enterprise) {
        String tokenString = tokenService.getToken(token);
        if (StringUtils.isEmpty(tokenString)) {
            return ResJson.errorAccessToken();
        }

        enterprise.setSmsFee(0D);
        enterprise.setNumOfNewCustomer(0L);
        enterpriseJpa.save(enterprise);
        return ResJson.successJson("add enterprise success");
    }

    @Override
    public ResJson findEnterpriseList(String token, Integer page, Integer size) {
        String tokenString = tokenService.getToken(token);
        if (StringUtils.isEmpty(tokenString)) {
            return ResJson.errorAccessToken();
        }

        Page<Enterprise> pageResult = enterpriseJpa.findAll(PageRequest.of(page, size));
        return ResJson.successJson("find enterprise list success", pageResult);
    }

    @Override
    public ResJson updateNumOfNewCustomer(JSONObject jsonObject) {
        String appId = jsonObject.getString("appId");
        Enterprise enterprise = enterpriseJpa.findByAppId(appId);
        if (null == enterprise) {
            return ResJson.failJson(4000, "企业不存在", null);
        }
        enterprise.setNumOfNewCustomer(enterprise.getNumOfNewCustomer() + 1);
        enterpriseJpa.save(enterprise);
        return ResJson.successJson("success");
    }


}
