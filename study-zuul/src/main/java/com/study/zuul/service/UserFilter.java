package com.study.zuul.service;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.study.util.IpUtils;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangpba
 * @date 2021-08-12
 */
@Component
public class UserFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(UserFilter.class);


    /**
     * 表示是否使用该过滤器
     */
    @Override
    public boolean shouldFilter() {
        // 获取请求上下文
        RequestContext context = RequestContext.getCurrentContext();
        // 获取到request
        HttpServletRequest request = context.getRequest();

        String user = request.getParameter("user");
        String uri = request.getRequestURI();

        // 若请求中包含/user8080路径，且没有user请求参数，则无法通过过滤 2021-08-15
        if (uri.contains("/user8080") && StringUtils.isEmpty(user)) {
            logger.warn("user用户为空");
            // 指定当前请求未通过zuul过滤，默认值为true
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }

    /**
     * 具体的过滤执行逻辑
     */
    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String ip = IpUtils.getRequestIp(request);
        logger.info("请求的ip:{}", ip);
        return ip;
    }

    /**
     * 过滤器类型
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序。返回值越小，执行顺序越优先。
     */
    @Override
    public int filterOrder() {
        return 1;
    }

}
