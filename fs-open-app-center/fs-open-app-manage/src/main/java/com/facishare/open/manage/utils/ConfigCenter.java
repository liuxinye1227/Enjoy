package com.facishare.open.manage.utils;

import com.facishare.open.manage.cons.CenterConstants;
import com.facishare.open.manage.kits.JsonKit;
import com.github.autoconf.ConfigFactory;
import org.apache.commons.collections.map.HashedMap;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xialf on 5/18/16.
 *
 * @author xialf
 */
public class ConfigCenter {
    private static final String COMMON_CONFIG = "fs-open-app-center-common";
    private static final String BIZ_CONFIG = "fs-open-app-manage-biz";
    private static String crmAppId;
    private static String crmComponentId;
    private static String testEnv;
    private static boolean isTestOn;


    /**
     * 发送运营消息的时间间隔.默认为100ms发送一个企业 .
     */
    private static int messageSendPeriod = 100;

    /**
     * 任务允许开始执行时间.
     */
    private static Integer taskExecuteStart = 2000;

    /**
     * 任务允许结束时间.
     */
    private static Integer taskExecuteEnd = 630;

    /**
     * taskSendFalg
     */
    private static Boolean taskSendFlag = false;

    /**
     * 开发查询所需APP ID
     */
    private static String appIdsForDevQuery = "";
    /**
     * 是否开启用户验证.
     */
    private static boolean needVerifyUserPermission = false;

    public static String UPLOAD_IMAGE_URL = "https://www.fxiaoke.com/open/assets/server/bj/imageCommonUpload";

    public static String BJ_IMAGE_VIEW_URL = "https://open.fxiaoke.com/fscdn/bj/imgTxtView?imgId=";
    public static String UPLOAD_ICON_URL = "https://www.fxiaoke.com/open/assets/server/bj/iconUpload";

    /**
     * 统计阅读数的最大图文消息数
     */
    public static int MAX_SIZE_FOR_COUNT_IMAGE_TEXT_READ_COUNT = 100;

    /**
     * 连续发送消息的最大阀值
     */
    public static Integer SEND_MESSAGE_MAX_THRESHOLD = 500;

    public static Integer SEND_MESSAGE_MAX_THRESHOLD_SLEEP_TIME = 5000;

    /**
     * 更新应用列表固定一个appId
     */
    public static String UPDATE_APP_LIST_FIXED_APP = "FSAID_9896e3";
    public static String OLD_DOWNLOAD_IMAGE_URL = "http://open.fxiaoke.com/fscdn/img?imgId=";


    /**
     * 服务号统计相关指标开关map
     */
    public static Map<String, Boolean> SERVICE_STATISTICS_SWITCH_MAP = new HashedMap();

    public static String getCrmAppId() {
        return crmAppId;
    }

    public static String getCrmComponentId() {
        return crmComponentId;
    }

    public static boolean isTestOn() {
        return isTestOn;
    }

    public static Boolean getTaskSendFlag() {
        return taskSendFlag;
    }

    public static String getAppIdsForDevQuery() {
        return appIdsForDevQuery;
    }

    public static String getTestEnv() { return testEnv; }

    static {
        ConfigFactory.getInstance().getConfig(COMMON_CONFIG, config -> {
            crmAppId = config.get("CRM_APP_ID");
            crmComponentId = config.get("CRM_COMPONENT_ID");
            isTestOn = config.getBool("IS_TEST_ON", false);
            testEnv = config.get("TEST_ENV");
            BJ_IMAGE_VIEW_URL = config.get("BJ_IMAGE_VIEW_URL", BJ_IMAGE_VIEW_URL);
            UPLOAD_IMAGE_URL = config.get("UPLOAD_IMAGE_URL", UPLOAD_IMAGE_URL);
            UPLOAD_ICON_URL = config.get("UPLOAD_ICON_URL", UPLOAD_ICON_URL);
        });
        ConfigFactory.getInstance().getConfig(BIZ_CONFIG, config -> {
            messageSendPeriod = config.getInt("MESSAGE_SEND_PERIOD", messageSendPeriod);
            taskExecuteStart = config.getInt("TASK_EXECUTE_START", taskExecuteStart);
            taskExecuteEnd = config.getInt("TASK_EXECUTE_END", taskExecuteEnd);
            taskSendFlag = config.getBool("TASK_SEND_FLAG", taskSendFlag);
            appIdsForDevQuery = config.get("APP_IDS_FOR_DEV_QUERY", appIdsForDevQuery);
            needVerifyUserPermission = config.getBool("NEED_VERIFY_USER_PERMISSION", needVerifyUserPermission);
            MAX_SIZE_FOR_COUNT_IMAGE_TEXT_READ_COUNT = config.getInt("MAX_SIZE_FOR_COUNT_IMAGE_TEXT_READ_COUNT", MAX_SIZE_FOR_COUNT_IMAGE_TEXT_READ_COUNT);
            SERVICE_STATISTICS_SWITCH_MAP = JsonKit.json2Object(config.get("SERVICE_STATISTICS_SWITCH_MAP", getDefaultServiceStatisticSwitchMap()), Map.class);
            SEND_MESSAGE_MAX_THRESHOLD = config.getInt("SEND_MESSAGE_MAX_THRESHOLD", SEND_MESSAGE_MAX_THRESHOLD);
            SEND_MESSAGE_MAX_THRESHOLD_SLEEP_TIME = config.getInt("SEND_MESSAGE_MAX_THRESHOLD_SLEEP_TIME", SEND_MESSAGE_MAX_THRESHOLD_SLEEP_TIME);
            UPDATE_APP_LIST_FIXED_APP = config.get("UPDATE_APP_LIST_FIXED_APP", UPDATE_APP_LIST_FIXED_APP);
            OLD_DOWNLOAD_IMAGE_URL = config.get("OLD_DOWNLOAD_IMAGE_URL", OLD_DOWNLOAD_IMAGE_URL);
        });
    }

    private ConfigCenter() {
    }

    public static boolean isCanRunningTask() {
        Calendar instance = Calendar.getInstance();
        //将当前时间转成四位数据，示例 12:02 转为 1202.用于转换.
        int now = instance.get(Calendar.HOUR_OF_DAY) * 100 + instance.get(Calendar.MINUTE);
        // 如果 配置时间为 06:00 - 18：00情况使用此判断.taskExecuteStart ＝600，taskExecuteEnd = 1800
        if (taskExecuteEnd > taskExecuteStart && now > taskExecuteStart && now < taskExecuteEnd) {
            return true;
        }
        // 如果 配置时间为 18：00 - 06:00 情况使用此判断.taskExecuteStart = 1800,taskExecuteEnd = 600
        if (taskExecuteEnd < taskExecuteStart && (now > taskExecuteStart || now < taskExecuteEnd)) {
            return true;
        }
        return false;
    }

    public static int getMessageSendPeriod() {
        return messageSendPeriod;
    }

    public static boolean needVerifyUserPermission() {
        return ConfigCenter.needVerifyUserPermission;
    }

    /**
     *
     * @return
     */
    private static final String getDefaultServiceStatisticSwitchMap() {
        Map<String, Boolean> map = new HashMap<>();
        map.put(CenterConstants.SERVICE_STATISTIC_GROUP_SEND_MSG_COUNT, Boolean.TRUE);
        map.put(CenterConstants.SERVICE_STATISTIC_GROUP_SEND_MSG_SERVICE_COUNT, Boolean.TRUE);
        map.put(CenterConstants.SERVICE_STATISTIC_GROUP_SEND_IMAGE_TEXT_MSG_COUNT, Boolean.TRUE);
        map.put(CenterConstants.SERVICE_STATISTIC_GROUP_SEND_IMAGE_TEXT_MSG_READ_COUNT, Boolean.TRUE);
        map.put(CenterConstants.SERVICE_STATISTIC_SERVICE_HUMAN_REPLY_COUNT, Boolean.TRUE);
        map.put(CenterConstants.SERVICE_STATISTIC_SERVICE_HUMAN_REPLY_SERVICE_COUNT, Boolean.TRUE);
        map.put(CenterConstants.SERVICE_STATISTIC_SERVICE_AUTO_REPLY_COUNT, Boolean.TRUE);
        map.put(CenterConstants.SERVICE_STATISTIC_SERVICE_AUTO_REPLY_SERVICE_COUNT, Boolean.TRUE);
        map.put(CenterConstants.SERVICE_STATISTIC_NEW_SERVICE_COUNT, Boolean.TRUE);
        map.put(CenterConstants.SERVICE_STATISTIC_SERVICE_COUNT, Boolean.TRUE);
        map.put(CenterConstants.SERVICE_STATISTIC_SERVICE_EA_COUNT, Boolean.TRUE);

        return JsonKit.object2json(map);
    }

    public static void main(String[] args) {
        System.out.println(getDefaultServiceStatisticSwitchMap());

        String s = "{\"groupSendImageTextMsgCount\":true,\"serviceEaCount\":true,\"serviceHumanReplyServiceCount\":true,\"serviceCount\":true,\"groupSendImageTextMsgReadCount\":true,\"serviceHumanReplyCount\":true,\"serviceAutoReplyServiceCount\":true,\"groupSendMsgServiceCount\":true,\"groupSendMsgCount\":true,\"serviceAutoReplyCount\":true,\"newServiceCount\":true}";
        Map map = JsonKit.json2Object(s, Map.class);
        System.out.println(JsonKit.object2json(map));
    }
}
