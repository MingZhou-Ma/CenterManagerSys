package com.wanguo.center_manager_sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.wanguo.center_manager_sys.pojo.Admin;
import com.wanguo.center_manager_sys.service.AdminService;
import com.wanguo.center_manager_sys.service.SmsService;
import com.wanguo.center_manager_sys.utils.GroupSmsUtil;
import com.wanguo.center_manager_sys.utils.ResJson;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 描述：
 *
 * @author Badguy
 */
@RestController
public class SmsController {

    private final SmsService smsService;

    @Autowired
    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @ApiOperation(value = "短信", notes = "发送短信接口")
    @ApiImplicitParam(name = "jsonObject", value = "jsonObject对象", required = true)
    @RequestMapping(value = "/api/sms/send", method = RequestMethod.POST)
    public ResJson sendSms(@RequestBody JSONObject jsonObject) {
        return smsService.groupSms(jsonObject);
    }
}
