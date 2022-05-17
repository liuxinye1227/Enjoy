//package com.facishare.open.manage.utils;
//
//import com.facishare.open.manage.model.MenuPageVO;
//import freemarker.template.Template;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @author dingc
// * @Description: 将模板数据放到内存里面，避免重复加载
// * @date 2015年8月28日 下午2:58:54
// */
//public class LocalCache {
//
//    public static ConcurrentHashMap<String, Template> templateFile = new ConcurrentHashMap<String, Template>();
//
//    /**
//     * 菜单缓存
//     */
//    public static ConcurrentHashMap<String, MenuPageVO> menuCache = new ConcurrentHashMap<String, MenuPageVO>();
//
//    private static Logger logger = LoggerFactory.getLogger(LocalCache.class);
//
//    public static void addCache(String key, Template value) {
//        logger.debug("addCache : key=" + key + "  value=" + value);
//        templateFile.put(key, value);
//    }
//
//    public static Template getCache(String key) {
//        return templateFile.get(key);
//    }
//
//    public static void deleteCache(String key) {
//        templateFile.remove(key);
//    }
//
//    public static ConcurrentHashMap<String, MenuPageVO> getMenuCache() {
//        return menuCache;
//    }
//
//    public static void setMenuCache(ConcurrentHashMap<String, MenuPageVO> menuCache) {
//        LocalCache.menuCache = menuCache;
//    }
//}
//
