package com.wanguo.center_manager_sys.utils;

import com.wanguo.center_manager_sys.pojo.Admin;
import lombok.Data;

import java.util.UUID;

/**
 * 描述：
 *
 * @author Badguy
 */
@Data
public class AccessToken {

    public static Long EXPIRED_TIME = 1440L;
    public static String ADMIN_REDIS_SESSION = "Bad guy";

    private String token;
    private Admin admin;

    public AccessToken(String token, Admin admin) {
        //this.token = UUID.randomUUID().toString().replaceAll("-", "");
        this.token = token;
        this.admin = admin;
    }

    public AccessToken() {}

}
