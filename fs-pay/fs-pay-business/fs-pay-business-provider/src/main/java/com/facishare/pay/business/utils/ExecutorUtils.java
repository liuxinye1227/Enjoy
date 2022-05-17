package com.facishare.pay.business.utils;

import com.facishare.open.common.thread.NamedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 公共线程池工具类
 * @author wangtao
 * @date 2015年8月28日 下午2:42:51
 */
public class ExecutorUtils {

    private static ExecutorService service = NamedThreadPool.newFixedThreadPool
            (Runtime.getRuntime().availableProcessors() * 4, "pay-business-notify");

    public static final Logger logger = LoggerFactory.getLogger(ExecutorUtils.class);
    
	public static void execute(Runnable command) {
		service.execute(command);
	}

	public static Future<?> submit(Runnable command) {
		return service.submit(command);
	}

}

