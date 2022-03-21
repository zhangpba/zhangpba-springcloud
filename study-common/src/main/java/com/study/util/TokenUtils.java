package com.study.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

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


    //设置过期时间
//    private static final long EXPIRE_DATE = 30 * 60 * 100000;
    private static final long EXPIRE_DATE = 3000;
    //token秘钥
    private static final String TOKEN_SECRET = "ZCfasfhuaUUHufguGuwu2020BQWE";

    /**
     * 获取token
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    public static String token(String username, String password) {

        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<String, Object>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("username", username)
                    .withClaim("password", password).withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }

    /**
     * @desc 验证token，通过返回true
     * @param token 需要校验的串
     * @return
     */
    public static boolean verify(String token) {
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
            System.out.println("username: " + username.asString() + ",password: " + password.asString());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String username = "zhangsan";
        String password = "123";
        String token = token(username, password);
        System.out.println(token);
        boolean b = verify(token);
        System.out.println("b:" + b);
        // 设置的token过期时间是3s,让程序等待4秒
        Thread.sleep(4000);
        boolean c = verify(token);
        System.out.println("c:" + c);
    }

}
