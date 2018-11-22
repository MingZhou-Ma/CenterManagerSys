package com.wanguo.center_manager_sys.service.impl;

import com.wanguo.center_manager_sys.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author Badguy
 */
@Service
public class TokenServiceImpl implements TokenService {

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public TokenServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void saveToken(String key, String value, Long l, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, l, timeUnit);
    }

    @Override
    public String getToken(String key) {
        //System.out.println("key:"+key);
        //System.out.println(stringRedisTemplate.opsForValue().get(key));
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void refreshToken(String key, Long timeout, TimeUnit timeUnit) {
        stringRedisTemplate.expire(key, timeout, timeUnit);
    }
}
