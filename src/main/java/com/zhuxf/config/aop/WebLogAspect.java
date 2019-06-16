package com.zhuxf.config.aop;

import ch.qos.logback.classic.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {

    private Logger logger = (Logger) LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * 预防同时请求  并发问题
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Order(4)
    @Pointcut(value = "execution(public * com.zhuxf..*.*(..))")
    public void WebLog() {
    }


    @Order(6)
    @Before("WebLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL_" + startTime + " : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD_" + startTime + " : " + request.getMethod());
        logger.info("IP_" + startTime + " : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD_" + startTime + " : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS_" + startTime + " : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "WebLog()")
    public void doAfterReturning(Object ret) {
        logger.info("resultData,{}",ret);
    }


}
