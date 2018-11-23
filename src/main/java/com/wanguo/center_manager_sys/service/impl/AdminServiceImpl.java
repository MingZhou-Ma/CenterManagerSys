package com.wanguo.center_manager_sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wanguo.center_manager_sys.jpa.AdminJpa;
import com.wanguo.center_manager_sys.pojo.Admin;
import com.wanguo.center_manager_sys.service.AdminService;
import com.wanguo.center_manager_sys.service.TokenService;
import com.wanguo.center_manager_sys.utils.AccessToken;
import com.wanguo.center_manager_sys.utils.MD5Util;
import com.wanguo.center_manager_sys.utils.ResJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author Badguy
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminJpa adminJpa;
    private final TokenService tokenService;

    @Autowired
    public AdminServiceImpl(AdminJpa adminJpa, TokenService tokenService) {
        this.adminJpa = adminJpa;
        this.tokenService = tokenService;
    }

    @Override
    public ResJson login(String username, String password) {
        //Admin a = adminJpa.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
        Admin a = adminJpa.findByUsernameAndPassword(username, password);
        if (null == a) {
            return ResJson.failJson(4000, "账号或密码错误", null);
        }
        // 从redis中查找token，如果找不到则存入，找到则直接返回
        String tokenString = tokenService.getToken(MD5Util.md5(AccessToken.ADMIN_REDIS_SESSION + ":" + a.getId()));
        if (StringUtils.isEmpty(tokenString)) {
            AccessToken accessToken = new AccessToken(MD5Util.md5(AccessToken.ADMIN_REDIS_SESSION + ":" + a.getId()), a);
            tokenService.saveToken(MD5Util.md5(AccessToken.ADMIN_REDIS_SESSION + ":" + a.getId()), JSONObject.toJSONString(accessToken), AccessToken.EXPIRED_TIME, TimeUnit.MINUTES);
            return ResJson.successJson("login success", accessToken);
        } else {
            tokenService.refreshToken(MD5Util.md5(AccessToken.ADMIN_REDIS_SESSION + ":" + a.getId()), AccessToken.EXPIRED_TIME, TimeUnit.MINUTES);
            return ResJson.successJson("login success", JSONObject.parseObject(tokenString, AccessToken.class));
        }

    }
}
