package com.data.chain.intecerceptor;

import com.data.chain.admin.util.ApiTokenUtil;
import com.data.chain.annotation.AdminAuth;
import com.data.chain.annotation.AdminAuthIgnore;
import com.data.chain.base.ResponseEnum;
import com.data.chain.config.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author peilizhu
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    ApiTokenUtil apiTokenUtil;

    @Override
    @SuppressWarnings("all")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ApiTokenUtil.setRequest(request);

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod method = (HandlerMethod) handler;
        // 1.方法是否有不校验注解
        AdminAuthIgnore AdminAuthIgnore = method.getMethodAnnotation(AdminAuthIgnore.class);
        if (AdminAuthIgnore != null) {
            return true;
        }

        // 2.方法是否有校验注解
        AdminAuth methodAnnotation = method.getMethodAnnotation(AdminAuth.class);
        if (Objects.nonNull(methodAnnotation)) {
            return validLoginStatus();
        }

        // 3.类是否有校验注解
        AdminAuth classAnnotation = method.getBeanType().getAnnotation(AdminAuth.class);
        if (Objects.nonNull(classAnnotation)) {
            return validLoginStatus();
        }
        return true;
    }

    /**
     * 登录状态校验
     **/
    private boolean validLoginStatus() {
        // 如果类上有登录校验, 则校验用户登录状态
        // 做基于session的登录状态校验
        Object userLoginInfo = apiTokenUtil.getUser();
        if (Objects.isNull(userLoginInfo)) {
            throw new CustomException(ResponseEnum.ERROR_405);
        } else {
            return true;
        }
    }
}
