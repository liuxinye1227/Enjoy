package com.facishare.pay.business.service.impl;

import com.facishare.open.common.storage.redis.RedisTemplate;
import com.facishare.pay.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by wangtao on 2015/10/15.
 */

public class BaseService {

    @Autowired
    RedisTemplate redisTemplate;

    private String CHARGE_KEY = "BUSINESS_CHARGE_NO_KEY_PRE";

    private AtomicLong AUTOID = new AtomicLong(-1);

    private AtomicLong ACTUAL_ID = null;

    private String format = "0000000000";

    public String getChargeNo() {

        String dateString = DateUtils.getCurrentDateString();
        if ((AUTOID.incrementAndGet())%1000 == 0) {
            // 重新获取ID
            String key = CHARGE_KEY + "_" + dateString;
            AUTOID = new AtomicLong(redisTemplate.incr(key));
            // 设置key值的过期时间
            redisTemplate.expire(key, 24*3600);
            ACTUAL_ID = new AtomicLong(AUTOID.get() * 1000);


        }
        // 年月日 + 固定10位

        DecimalFormat df = new DecimalFormat(format);

        return dateString + df.format(ACTUAL_ID.incrementAndGet());
    }

}
