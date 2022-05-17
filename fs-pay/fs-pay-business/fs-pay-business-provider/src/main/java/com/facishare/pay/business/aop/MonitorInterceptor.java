package com.facishare.pay.business.aop;

import com.facishare.monitor.Monitor;
import com.facishare.pay.common.result.ModelResult;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * 监控
 * @author wangt
 */
public class MonitorInterceptor implements MethodInterceptor {

    private static Logger logger = LoggerFactory.getLogger(MonitorInterceptor.class);

    private static final String MONITOR_KEY_PRE = "pay.business.";

    private static final String MONITOR_KEY_SUFFIX_RATE = ".total.sum"; //频率

    private static final String MONITOR_KEY_SUFFIX_TIME = ".time.avg"; // 耗时

    private static final String MONITOR_KEY_SUFFIX_RIGHT = ".success.sum"; // 成功次数

    private static final String MONITOR_KEY_SUFFIX_COUNT = ".fail.sum"; // 异常次数

    @Value("${fs.apps.monitor.switch.on}")
    private boolean MONITOR_RUNNING = true;
    
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        
        Long startTime = System.currentTimeMillis();
        Object result = null;
        
        Object[] o = invocation.getArguments();
        logger.info("class:{}, method : {}", invocation.getMethod().getName(), o);
        try {
            result = invocation.proceed();
        } catch (Exception e) {
            
            logger.error("proceed error", e);
        }
        logger.info("method :[{}] cost:[{}] result:[{}]", invocation.getMethod().getName(),
            (System.currentTimeMillis()-startTime), result);
        // 监控开关
        if (!MONITOR_RUNNING) {
            return result;
        }
        String monitoryRateKey = MONITOR_KEY_PRE + invocation.getMethod().getName().toLowerCase() +
                MONITOR_KEY_SUFFIX_RATE;
        String monitoryTimeKey = MONITOR_KEY_PRE + invocation.getMethod().getName().toLowerCase() +
                MONITOR_KEY_SUFFIX_TIME;
        String monitoryRightKey = MONITOR_KEY_PRE + invocation.getMethod().getName().toLowerCase() +
                MONITOR_KEY_SUFFIX_RIGHT;
        try {
            // 统计频次
        	Monitor.push(monitoryRateKey, 1);
            
            // 统计平均数
        	Monitor.push(monitoryTimeKey, (int)(System.currentTimeMillis() - startTime));
            
            if (result instanceof ModelResult) {
                //如果处理结果 是期望的结果 ，消息服务处理成功
                Monitor.push(monitoryRightKey, 1);
                // 参数非法请求
                String monitoryIllegalParamKey =
                    MONITOR_KEY_PRE + invocation.getMethod().getName().toLowerCase()
                        + MONITOR_KEY_SUFFIX_COUNT;
                Monitor.push(monitoryIllegalParamKey, 1);
            } else {
            	Monitor.push(monitoryRightKey, result == null ? 0 : 1);
            }
            
        } catch (Exception e) {
            logger.error("monitor error", e);
        }
        return result;

    }

}
