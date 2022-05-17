package com.facishare.open.app.center.adapter.model.aop;

import com.facishare.open.common.result.BaseResult;
import com.facishare.open.common.result.SystemErrorCode;
import com.facishare.open.common.result.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * Author: Ansel Qiao Create Time: 15/7/26
 */
@Slf4j
@Component
@Aspect
public class LogAspect {
    private final LogAspectHelper logAspectHelper = new LogAspectHelper();

    /**
     * 记录请求参数和返回结果.
     */
    @Around("execution(* com.facishare.open.app.center.adapter.model.rest.controller.*.*(..))")
    public Object callInAround(ProceedingJoinPoint point) throws Throwable {
        MDC.put("requestId", LogAspectHelper.getShortUUID());
        final String paramInfo = String.format(logAspectHelper.getParamsFmt(point), point.getArgs());
        final String sigName = logAspectHelper.getSignature(point);
        log.info("==== Start [{}]: {}", sigName, paramInfo);
        final long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = point.proceed();
        } catch (BizException e) {
            log.warn("fail to finish [{}]: {}", sigName, paramInfo, e);
            result = new BaseResult<>(e);
        } catch (Exception e) {
            log.error("fail to finish [{}]: {}", sigName, paramInfo, e);
            result = new BaseResult<>(SystemErrorCode.SYSTEM_ERROR);
        } finally {
            final long endTime = System.currentTimeMillis();
            log.info("==== End [{}]: {}, result={}, cost=[{}]", sigName, paramInfo, result, endTime - startTime);
        }
        return result;
    }

    /**
     * 记录请求参数和返回结果.
     */
    @Around("execution(* com.facishare..*.*(..)) && !execution(* com.facishare.open.app.center.adapter..*.*(..))")
    public Object callOutAround(ProceedingJoinPoint point) throws Throwable {

        final String paramInfo = String.format(logAspectHelper.getParamsFmt(point), point.getArgs());
        final String sigName = logAspectHelper.getSignature(point);
        log.info("==== Call start [{}]: {}", sigName, paramInfo);
        Object result = null;
        final long startTime = System.currentTimeMillis();
        try {
            result = point.proceed();
        } catch (BizException e) {
            log.warn("fail to call [{}]: {}", sigName, paramInfo, e);
            throw e;
        } catch (Exception e) {
            log.error("fail to call [{}]: {}", sigName, paramInfo, e);
            throw e;
        } finally {
            final long endTime = System.currentTimeMillis();
            log.info("==== Call end [{}]: {}, result={}, cost=[{}]", sigName, paramInfo, result, endTime - startTime);
        }
        return result;
    }
}
