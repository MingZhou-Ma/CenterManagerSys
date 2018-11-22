package com.wanguo.center_manager_sys.service;

import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author Badguy
 */
public interface TokenService {

    void saveToken(String key, String value, Long timeout, TimeUnit timeUnit);

    String getToken(String key);

    void refreshToken(String key, Long timeout, TimeUnit timeUnit);
}
