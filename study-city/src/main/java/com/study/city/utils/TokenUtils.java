package com.study.city.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.study.city.user.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Token帮助类
 *
 * @author zhangpba
 * @date 2022-02-21
 */
public class TokenUtils {
    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    // 设置过期时间:一个小时有效时间
    public static final long EXPIRE_DATE = 60 * 60 * 100000;

    // token秘钥
    public static final String TOKEN_SECRET = "chengxuyuandeziwoxiuyang2023";

    // token的名字
    public static final String TOKEN_NAME = "token";

    /**
     * 获取token
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    public static String getToken(String username, String password) {
        // Algorithm.HMAC256():使用HS256生成token,密钥则是用户的密码，唯一密钥的话可以保存在服务端。
        // withAudience()存入需要保存在token的信息，这里我把用户ID存入token中
        // 登陆的时候调用此方法，来获取token
        String token = "";
        try {
            Date start = new Date();
            // 过期时间，一个小时有效时间
            long currentTime = System.currentTimeMillis() + EXPIRE_DATE;
            Date end = new Date(currentTime);

            // 秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<String, Object>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            // 携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("username", username)
                    .withClaim("password", password)
                    .withIssuedAt(start)
                    .withExpiresAt(end)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }

    /**
     * @param token 需要校验的串
     * @return
     * @desc 验证token，通过返回true
     */
    public static SysUser verifyUser(String token) {
        SysUser sysUser = new SysUser();
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            // 验证token
            DecodedJWT verify = verifier.verify(jwt);
            // 可以解析出token中的用户信息
            // 根据设置的key获取对应的value
            Claim username = verify.getClaim("username");
            Claim password = verify.getClaim("password");
            logger.info("username: {},password：{}", username.asString(), password.asString());
            sysUser.setUsername(username.asString());
            sysUser.setPassword(password.asString());
            return sysUser;
        } catch (Exception e) {
            e.printStackTrace();
            return sysUser;
        }
    }


    /**
     * @param token 需要校验的串
     * @return
     * @desc 验证token，通过返回true
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            // 验证token
            DecodedJWT verify = verifier.verify(jwt);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String username = "zhangsan";
        String password = "123";
        String token = getToken(username, password);
        System.out.println(token);
        boolean b = verify(token);
        System.out.println("b:" + b);
        // 设置的token过期时间是3s,让程序等待4秒
        Thread.sleep(4000);
        boolean c = verify(token);
        System.out.println("c:" + c);
    }
}
