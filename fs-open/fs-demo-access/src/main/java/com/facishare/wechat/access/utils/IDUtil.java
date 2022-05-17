package com.facishare.wechat.access.utils;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by lif on 2015/9/28.
 */
public class IDUtil {
    public static String nextId(){
//        ThreadLocalRandom tlr = ThreadLocalRandom.current();
//        long rand = tlr.nextLong(1, 1000000000000l);
//        return rand;
        return UUID.randomUUID().toString().replace("-","");
    }
}
