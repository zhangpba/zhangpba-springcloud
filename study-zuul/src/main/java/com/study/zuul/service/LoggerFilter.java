package com.study.zuul.service;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * zuul过滤器，必须继承ZuulFilter父类
 * 当前类型的对象必须交由spring容器管理，使用注解@Component
 * 继承父类后，必须实现父类中定义的4个抽象方法：shouldFilter、 run、 filterType、 filterOrder
 *
 * @author zhangpba
 * @date 2021-06-01
 */
@Component
public class LoggerFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoggerFilter.class);

    /**
     * 返回boolean类型，代表当前filter是否生效
     * 默认值为false,返回true代表开启filter
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * run方法就是过滤的具体逻辑
     *
     * @return 可以返回任意的对象，当前实现忽略
     */
    @Override
    public Object run() {
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest request = rc.getRequest();

        logger.info("网关过滤器记录日志：LogFilter1.....method={},url={}", request.getMethod(), request.getRequestURL().toString());
        // 打印出来的：网关过滤器记录日志：LogFilter1.....method=GET,url=http://localhost:8888/api/client/getFile
        //可以记录日志、鉴权、给维护人员记录提供定位协助、统计功能
        return null;
    }

    /**
     * 过滤器的类型。可选值有：
     * pre - 前置过滤
     * route - 路由后过滤
     * error - 异常过滤
     * post - 远程服务调用后过滤
     *
     * @return 过滤器的类型
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 同种类的过滤器的执行顺序
     * 按照返回值的自然顺序执行
     *
     */
    @Override
    public int filterOrder() {
        return 0;
    }
}
