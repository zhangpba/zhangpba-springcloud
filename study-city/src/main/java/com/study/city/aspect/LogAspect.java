package com.study.city.aspect;

import com.study.common.annotation.Log;
import com.study.common.enums.BusinessType;
import com.study.common.utils.IpUtils;
import com.study.common.utils.ServletUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 操作日志记录处理
 *
 * @author zhangpba
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult) {
        // 类名
        String className = joinPoint.getTarget().getClass().getName();
        // 方法名
        String methodName = joinPoint.getSignature().getName();
        // 模块
        String title = controllerLog.title();
        // 业务操作类型
        BusinessType businessType = controllerLog.businessType();

        // 来源IP
        String ip = IpUtils.getRequestIp(ServletUtils.getRequest());
        log.info("切面记录日志...... : 来自【{}】的用户 在【{}】类中执行【{}】方法来完成【{}】的【{}】操作", ip, className, methodName, title, businessType);
        // todo 记录系统日志

    }


    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
        log.info("打印出 doAfterThrowing......controllerLog: {}", controllerLog);
        log.info("打印出 doAfterThrowing......joinPoint: {}", joinPoint);
        // todo 记录系统日志

    }


}
