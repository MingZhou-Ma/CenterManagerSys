package com.wanguo.center_manager_sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.wanguo.center_manager_sys.pojo.Enterprise;
import com.wanguo.center_manager_sys.service.EnterpriseService;
import com.wanguo.center_manager_sys.utils.ResJson;
import com.wanguo.center_manager_sys.utils.ValidationUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 描述：
 *
 * @author Badguy
 */
@RestController
public class EnterpriseController {

    private final EnterpriseService enterpriseService;

    private final MessageSource messageSource;

    @Autowired
    public EnterpriseController(EnterpriseService enterpriseService, MessageSource messageSource) {
        this.enterpriseService = enterpriseService;
        this.messageSource = messageSource;
    }

    @ApiOperation(value = "添加企业", notes = "添加企业")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "enterprise", value = "企业实体enterprise", required = true, dataType = "Enterprise")
    })
    @RequestMapping(value = "/api/enterprise/add", method = RequestMethod.POST)
    public ResJson addEnterprise(@RequestParam String token, @RequestBody @Valid Enterprise enterprise, BindingResult bindingResult) {
        ResJson resJson = ValidationUtil.validation(bindingResult, messageSource);
        if (!resJson.getCode().equals(1000)) {
            return resJson;
        }
        return enterpriseService.addEnterprise(token, enterprise);
    }

    @ApiOperation(value = "查询企业", notes = "查询企业列表")
//    @ApiImplicitParams(value = {
//            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "page", value = "起始页", required = true, dataType = "int"),
//            @ApiImplicitParam(name = "size", value = "每页大小", required = true, dataType = "int")
//    })
    @ApiImplicitParam(name = "jsonObject", value = "jsonObject对象", required = true, dataType = "JSONObject")
    @RequestMapping(value = "/api/enterprise/list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResJson findEnterpriseList(@RequestBody JSONObject jsonObject) {

        //return enterpriseService.findEnterpriseList(token, page, size);
        return null;
    }

    @ApiOperation(value = "更新新客户数", notes = "更新新客户数")
    @ApiImplicitParam(name = "jsonObject", value = "jsonObject对象", required = true)
    @RequestMapping(value = "/api/enterprise/update", method = RequestMethod.POST)
    public ResJson updateNumOfNewCustomer(@RequestBody JSONObject jsonObject) {
        return enterpriseService.updateNumOfNewCustomer(jsonObject);
    }
}
