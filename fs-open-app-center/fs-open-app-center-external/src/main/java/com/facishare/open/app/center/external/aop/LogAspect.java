package com.facishare.open.app.center.external.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Author: Ansel Qiao Create Time: 15/7/26
 */
public class LogAspect {
    private static final Logger timeMonitorLog = LoggerFactory.getLogger("TIME_MONITOR_LOG");
    private static final Logger loggerAll = LoggerFactory.getLogger(LogAspect.class);

    public Object around(ProceedingJoinPoint point) throws Throwable {
        String className = point.getTarget().getClass().getSimpleName();
        if (className.endsWith("Impl")) {
            className = className.substring(0, className.length() - 4);
        }
        String methodName = className + "." + point.getSignature().getName();
        loggerAll.info("==== Received start [methodName:{}]. params: {}.", methodName,
                Arrays.toString(point.getArgs()));

        long startTime = System.currentTimeMillis();
        Object result = point.proceed();
        long endTime = System.currentTimeMillis();

        timeMonitorLog.info("timeCost [methodName:{}]. params: {}. time: {}", methodName,
                Arrays.toString(point.getArgs()), (endTime - startTime));
        loggerAll.info("==== Received end [methodName:{}]. params: {}, result: {}.", methodName,
                Arrays.toString(point.getArgs()), result);
        return result;
    }
}
