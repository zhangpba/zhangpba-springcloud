package com.study.city.interceptor;

import com.study.starter.utils.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 访客拦截器
 * <p>
 * 一.什么是拦截器：拦截器（Interceptor）类似于Servlet中的过滤器，主要用于拦截客户请求并做出相应的处理。
 * 与过滤器有如下区别：
 * 1.过滤器只能在容器初始化时被调用一次，在action的生命周期中，而拦截器可以多次被调用。
 * 2.过滤器可以对几乎所有的请求起作用，拦截器只能对action请求起作用。
 * 3.过滤器不能访问action上下文、值栈里的对象，而拦截器可以访问。
 * 4.过滤器依赖于servlet容器，而拦截器不依赖于servlet容器。
 * 5.过滤器是基于函数回调，而拦截器是基于java的反射机制的。
 * 6.过滤器不能获取IOC容器中的各个bean，而拦截器可以，这点很重要，在拦截器里注入一个service，可以调用业务逻辑。
 * <p>
 * 二.拦截器特点
 * 1.请求到达经过拦截器，响应回来也经过拦截器
 * 2.只能拦截控制器相关请求不能拦截JSP请求
 * 3.拦截器可以中断用户请求轨迹
 */
@Component
public class VisitorInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(VisitorInterceptor.class);

    @Value("${black-list.ip}")
    private String ipBlacklist;

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     * @功能描述 preHandle方法 返回false:拦截不往下继续进行;返回true:继续往下进行，进入controller
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("拦截器 获取控制器的名称：{}", handler);
        if (!checkIp(request)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 校验是ip是否在和名单内
     *
     * @param request web请求
     * @return
     */
    public boolean checkIp(HttpServletRequest request) {
        AtomicBoolean isIp = new AtomicBoolean(false);
        String requestIp = IpUtils.getRequestIp(request);
        logger.info("访问 ip：{}", requestIp);
        String[] ips = ipBlacklist.split(",");
        Arrays.asList(ips).forEach(ip -> {
            if (ip.equals(requestIp)) {
                isIp.set(true);
            }
        });
        if (!isIp.get()) {
            logger.info("没有访问权限,该IP {} 已被列为黑名单", requestIp);
        }
        return isIp.get();
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
