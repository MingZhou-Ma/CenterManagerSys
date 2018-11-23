package com.wanguo.center_manager_sys.controller;

import com.wanguo.center_manager_sys.pojo.Admin;
import com.wanguo.center_manager_sys.service.AdminService;
import com.wanguo.center_manager_sys.utils.ResJson;
import com.wanguo.center_manager_sys.utils.ValidationUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class AdminController {

    private final MessageSource messageSource;

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService, MessageSource messageSource) {
        this.adminService = adminService;
        this.messageSource = messageSource;
    }

    /*@ApiOperation(value = "登录", notes = "管理员登录")
    @ApiImplicitParam(name = "admin", value = "管理员实体admin", required = true, dataType = "Admin")*/
    @RequestMapping(value = "/api/admin/login", method = RequestMethod.POST)
    public ResJson login(@RequestParam String username, @RequestParam  String password) {
//        ResJson resJson = ValidationUtil.validation(bindingResult, messageSource);
//        if (!resJson.getCode().equals(1000)) {
//            return resJson;
//        }
        return adminService.login(username, password);
    }
}
