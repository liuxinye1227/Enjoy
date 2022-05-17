package com.facishare.open.manage.controller;

import com.facishare.open.broker.message.model.SendControlVO;
import com.facishare.open.broker.message.model.bean.SendControlDO;
import com.facishare.open.broker.message.result.SendControlListResult;
import com.facishare.open.broker.message.service.SendControlService;
import com.facishare.open.common.storage.redis.RedisTemplate;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.cons.CenterConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/open/manage/rest/sendcontrol")
public class SendController extends BaseController {

    @Autowired
    private SendControlService sendControlService;

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping(value = "saveSendControl", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult saveSendControl(@RequestBody SendControlVO sendControlVO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        SendControlVO sendControl = sendControlVO;

        //保存数据到数据库
        sendControlService.addSendControl(sendControl);

        //设置缓存
        if (CenterConstants.SEND_MESSAGE_STATUS_ENABLE.equals("" + sendControlVO.getStatus())) {
            redisTemplate.set(sendControlVO.getFullMethodName(), "" + sendControlVO.getStatus());
        }

        return SUCCESS;
    }

    /**
     * 模板列表
     *
     * @return
     */
    @RequestMapping("sendList")
    @ResponseBody
    public AjaxResult list() {
        SendControlListResult sendControlListResult = sendControlService.querySendControlList();
        List<SendControlDO> list = sendControlListResult.getSendControlDOList();
        return new AjaxResult(list);
    }

    /**
     * 发送控制信息删除
     *
     * @param id 模板seqid
     * @return
     * @throws IOException
     */
    @RequestMapping("sendControlDelete")
    @ResponseBody
    public AjaxResult sendControlDelete(@RequestBody SendControlVO sendControlVO, HttpServletRequest request, HttpServletResponse response) throws IOException {

        //删除记录
        sendControlService.deleteSendControl(sendControlVO.getId());
        //删除缓存
        redisTemplate.remove(sendControlVO.getFullMethodName());

        return SUCCESS;
    }

    @RequestMapping("sendControlUpdate")
    @ResponseBody
    public AjaxResult sendControlUpdate(@RequestBody SendControlVO sendControlVO, HttpServletRequest request, HttpServletResponse response) throws IOException {

        //更新数据
        sendControlService.updateSendControl(sendControlVO);
        //设置缓存
        if (CenterConstants.SEND_MESSAGE_STATUS_ENABLE.equals("" + sendControlVO.getStatus())) {

            redisTemplate.set(sendControlVO.getFullMethodName(), "" + sendControlVO.getStatus());
        }

        return SUCCESS;
    }
}

