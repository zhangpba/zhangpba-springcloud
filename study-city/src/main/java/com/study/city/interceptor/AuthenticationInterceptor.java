package com.study.city.interceptor;

import com.study.city.annotation.LoginToken;
import com.study.city.annotation.PassToken;
import com.study.city.user.enums.UserCodeEnum;
import com.study.city.utils.TokenUtils;
import com.study.common.exception.CustomException;
import com.study.common.utils.JsonUtils;
import com.study.common.utils.StringUtils;
import com.study.common.web.ResponseEnum;
import com.study.common.web.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author zhangpba
 * @description 获取token并验证token 拦截器
 * @date 2023/1/20
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从http请求头中取出token
        String token = request.getHeader(TokenUtils.TOKEN_NAME);
//        String token = request.getParameter(TokenUtils.TOKEN_NAME);
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
                if (StringUtils.isEmpty(token)) {
                    throw new CustomException(UserCodeEnum.ERROR_407.getCode(), UserCodeEnum.ERROR_407.getMsg());
                }
                // 验证token
                boolean isExpired = TokenUtils.verify(token);
                if (!isExpired) {
                    returnJson(response);
                }
            }
        }
        return true;
    }

    /**
     * 异常返回固定格式的信息
     *
     * @param response
     */
    private void returnJson(HttpServletResponse response) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ResponseMessage responseMessage = ResponseMessage.error(ResponseEnum.ERROR_405.getCode(), ResponseEnum.ERROR_405.getMsg());
        try {
            writer = response.getWriter();
            String jsonRresponse = JsonUtils.obj2Json(responseMessage);
            writer.print(jsonRresponse);
        } catch (IOException e) {
            new CustomException(-1, "拦截器输出流异常");
            logger.error("拦截器输出流异常" + e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
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
