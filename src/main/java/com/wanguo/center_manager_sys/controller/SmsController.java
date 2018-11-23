package com.wanguo.center_manager_sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.wanguo.center_manager_sys.service.SmsService;
import com.wanguo.center_manager_sys.utils.ResJson;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 描述：
 *
 * @author Badguy
 */
@RestController
@ApiIgnore
public class SmsController {

    private final SmsService smsService;

    @Autowired
    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @ApiOperation(value = "短信", notes = "发送短信接口")
    @ApiImplicitParam(name = "jsonObject", value = "jsonObject对象", required = true)
    @RequestMapping(value = "/api/sms/send", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResJson sendSms(@RequestBody JSONObject jsonObject) {
        return smsService.groupSms(jsonObject);
    }
}
