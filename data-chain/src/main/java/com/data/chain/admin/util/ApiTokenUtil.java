package com.data.chain.admin.util;

//import com.data.chain.config.RedisUtils;

import com.data.chain.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author peilizhu
 */
@Component
public class ApiTokenUtil {

    @Autowired
    private RedisUtils redisUtils;

    private static final ThreadLocal<HttpServletRequest> TOKEN_THREAD_LOCAL = new ThreadLocal<>();

    public static void setRequest(HttpServletRequest request) {
        TOKEN_THREAD_LOCAL.set(request);
    }

    /**
     * 获取登陆用户信息
     *
     * @return 返回用户信息
     */
    public ApiLoginInfo getUser() {
        String token = TOKEN_THREAD_LOCAL.get().getHeader("token");
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return redisUtils.get(token);
    }

    /**
     * 缓存用户登陆状态
     *
     * @param apiLoginInfo 登陆信息
     */
    public String addUser(ApiLoginInfo apiLoginInfo) {
        // 删除旧token对应的用户信息
        String oldToken = redisUtils.get("TOKEN_KEY_" + apiLoginInfo.getUserId());
        if(StringUtils.isNotBlank(oldToken)){
            redisUtils.delete(oldToken);
        }
        // 保存新值到新token
        String token = DigestUtils.md5DigestAsHex(("" + apiLoginInfo.getUserId() + System.currentTimeMillis()).getBytes());
        redisUtils.set("TOKEN_KEY_" + apiLoginInfo.getUserId(), token);
        redisUtils.set(token, apiLoginInfo);
        return token;
    }
}
