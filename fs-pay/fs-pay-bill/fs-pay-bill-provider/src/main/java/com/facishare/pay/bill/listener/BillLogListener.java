package com.facishare.pay.bill.listener;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.facishare.pay.bill.dao.BillLogDAO;
import com.facishare.pay.bill.model.BillLogDo;
import com.facishare.pay.bill.model.vo.BillNoticeVO;
import com.google.gson.Gson;


/**
 * 消息监听  
 * 监听消息类型为 PAY.PROVIDER.BANK
 * @author guom 
 * @date 2015-10-21
 */
public class BillLogListener {

    public static final Logger logger = LoggerFactory.getLogger(BillLogListener.class);
    
    private static Gson gson = new Gson(); 
    
    @Autowired
    private BillLogDAO billLogDao;
    
    /**
     * 消息监听入口
     * */
    public void execute(String message) {
        BillLogDo billLogDo = null;
        try {
            BillNoticeVO billNoticeVo = gson.fromJson(message, BillNoticeVO.class);
            billLogDo = new BillLogDo(billNoticeVo.getEnterpriseAccount(), billNoticeVo.getFsUserId(), Calendar.getInstance(),
                    billNoticeVo.getRequestTime(), billNoticeVo.getAmount(), billNoticeVo.getBalance()
                    , billNoticeVo.getBusiNo(), billNoticeVo.getOrderNo(), billNoticeVo.getTransType());
            billLogDao.addBillLog(billLogDo);
        } catch (Exception e) {
            logger.error("发送参数{}, 异常：{}", message.toString(), e.getMessage());
        }
        
        
    }
    
}
