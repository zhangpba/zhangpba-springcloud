package com.study.city.interceptor;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.study.city.annotation.LoginToken;
import com.study.city.annotation.PassToken;
import com.study.city.config.exception.CustomException;
import com.study.city.user.entity.SysUser;
import com.study.city.user.mapper.SysUserMapper;
import com.study.city.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author zhangpba
 * @description 获取token并验证token 拦截器
 * @date 2023/1/20
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从http请求头中取出token
        String token = request.getParameter(TokenUtils.TOKEN_NAME);
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(LoginToken.class)) {
            LoginToken userLoginToken = method.getAnnotation(LoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new CustomException(401, "无token，请重新登录");
                }
                // 获取token中的username password
                SysUser sysUser;
                try {
                    sysUser = TokenUtils.verifyUser(token);
                } catch (JWTDecodeException j) {
                    throw new CustomException(401, "验证失败");
                }

                SysUser user = sysUserMapper.login(sysUser.getUsername(), sysUser.getPassword());
                if (user == null) {
                    throw new CustomException(401, "用户不存在，请重新登录");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
