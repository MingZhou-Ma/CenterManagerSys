package com.wanguo.center_manager_sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wanguo.center_manager_sys.exception.JsonParseException;
import com.wanguo.center_manager_sys.jpa.BillFlowJpa;
import com.wanguo.center_manager_sys.jpa.EnterpriseJpa;
import com.wanguo.center_manager_sys.pojo.BillFlow;
import com.wanguo.center_manager_sys.pojo.Enterprise;
import com.wanguo.center_manager_sys.service.EnterpriseService;
import com.wanguo.center_manager_sys.service.TokenService;
import com.wanguo.center_manager_sys.utils.ParamUtil;
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

    private final BillFlowJpa billFlowJpa;

    @Autowired
    public EnterpriseServiceImpl(TokenService tokenService, EnterpriseJpa enterpriseJpa, BillFlowJpa billFlowJpa) {
        this.tokenService = tokenService;
        this.enterpriseJpa = enterpriseJpa;
        this.billFlowJpa = billFlowJpa;
    }

    @Override
    public ResJson addEnterprise(String token, Enterprise enterprise) {
        String tokenString = tokenService.getToken(token);
        if (StringUtils.isEmpty(tokenString)) {
            return ResJson.errorAccessToken();
        }
        if (null != enterpriseJpa.findByAppId(enterprise.getAppId())) {
            return ResJson.failJson(4000, "企业已存在", null);
        }

        enterprise.setSmsFee(0D);
        enterprise.setNumOfNewCustomer(0L);
        enterpriseJpa.save(enterprise);
        return ResJson.successJson("add enterprise success");
    }

    @Override
    public ResJson delEnterprise(String token, Integer id) {
//        String token;
//        Integer id;
//        try {
//            token = (String) ParamUtil.getFromJson(jsonObject, "token", String.class);
//            id = (Integer) ParamUtil.getFromJson(jsonObject, "id", Integer.class);
//        } catch (JsonParseException e) {
//            return ResJson.errorRequestParam(e.getMessage());
//        }
        String tokenString = tokenService.getToken(token);
        if (StringUtils.isEmpty(tokenString)) {
            return ResJson.errorAccessToken();
        }

        Enterprise enterprise = enterpriseJpa.getOne(id);
        if (null == enterprise) {
            return ResJson.failJson(4000, "企业不存在", null);
        }
        enterpriseJpa.delete(enterprise);
        return ResJson.successJson("delete enterprise success", null);
    }

    @Override
    public ResJson findEnterpriseList(String token, Integer page, Integer size) {
//        String token;
//        Integer page;
//        Integer size;
//        try {
//            token = (String) ParamUtil.getFromJson(jsonObject, "token", String.class);
//            page = (Integer) ParamUtil.getFromJsonWithDefault(jsonObject, "page", 0, Integer.class);
//            size = (Integer) ParamUtil.getFromJsonWithDefault(jsonObject, "size", 10, Integer.class);
//        } catch (JsonParseException e) {
//            return ResJson.errorRequestParam(e.getMessage());
//        }
        String tokenString = tokenService.getToken(token);
        if (StringUtils.isEmpty(tokenString)) {
            return ResJson.errorAccessToken();
        }

        Page<Enterprise> pageResult = enterpriseJpa.findAll(PageRequest.of(page, size));
        return ResJson.successJson("find enterprise list success", pageResult);
    }

    @Override
    public ResJson findBillFlowListByEnterprise(String token, Integer page, Integer size, Integer id) {
//        String token;
//        Integer page;
//        Integer size;
//        Integer id;
//        try {
//            token = (String) ParamUtil.getFromJson(jsonObject, "token", String.class);
//            page = (Integer) ParamUtil.getFromJsonWithDefault(jsonObject, "page", 0, Integer.class);
//            size = (Integer) ParamUtil.getFromJsonWithDefault(jsonObject, "size", 10, Integer.class);
//            id = (Integer) ParamUtil.getFromJson(jsonObject, "id", Integer.class);
//        } catch (JsonParseException e) {
//            return ResJson.errorRequestParam(e.getMessage());
//        }
        String tokenString = tokenService.getToken(token);
        if (StringUtils.isEmpty(tokenString)) {
            return ResJson.errorAccessToken();
        }

        Enterprise enterprise = enterpriseJpa.getOne(id);
        if (null == enterprise) {
            return ResJson.failJson(4000, "企业不存在", null);
        }
        Page<BillFlow> pageResult = billFlowJpa.findAllByEnterprise(enterprise, PageRequest.of(page, size));
        return ResJson.successJson("success", pageResult);
    }

    @Override
    public ResJson updateNumOfNewCustomer(JSONObject jsonObject) {
        String appId;
        try {
            appId = (String) ParamUtil.getFromJson(jsonObject, "appId", String.class);
        } catch (JsonParseException e) {
            return ResJson.errorRequestParam(e.getMessage());
        }
        Enterprise enterprise = enterpriseJpa.findByAppId(appId);
        if (null == enterprise) {
            return ResJson.failJson(4000, "企业不存在", null);
        }
        enterprise.setNumOfNewCustomer(enterprise.getNumOfNewCustomer() + 1);
        //enterprise.setTotalFee(enterprise.getSmsFee() + enterprise.getNumOfNewCustomer() * 0.8);
        enterpriseJpa.save(enterprise);
        return ResJson.successJson("success");
    }

}
