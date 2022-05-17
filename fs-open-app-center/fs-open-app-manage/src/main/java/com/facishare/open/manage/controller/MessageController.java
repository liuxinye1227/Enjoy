package com.facishare.open.manage.controller;

import com.facishare.open.app.center.api.result.AppResult;
import com.facishare.open.app.center.api.service.OpenAppService;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.BaseResult;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.utils.ConfigCenter;
import com.facishare.open.material.api.enums.CreatorTypeEnum;
import com.facishare.open.material.api.enums.MaterialTypeEnum;
import com.facishare.open.material.api.enums.MessageSendTypeEnum;
import com.facishare.open.material.api.model.vo.AccepterVO;
import com.facishare.open.material.api.model.vo.MessageVO;
import com.facishare.open.material.api.service.MaterialMessageService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * Created by liqiulin on 2017/3/6.
 */
@Controller
@RequestMapping("/open/manage/rest/message")
public class MessageController extends BaseController {
    @Resource
    private MaterialMessageService materialMessageService;

    @Resource
    private OpenAppService openAppService;

    @RequestMapping(value = "/material/imageText", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult sendMaterialImageTexMessage(@RequestParam(value = "senderAppId", required = false) String senderAppId,
                                                  @RequestParam(value = "senderFsEa", required = false) String senderFsEa,
                                                  @RequestParam(value = "materialId", required = false) String materialId,
                                                  @RequestParam(value = "acceptorFile", required = false) MultipartFile acceptorFile) {
        checkParam(senderAppId, "senderAppId is null");
        checkParam(materialId, "materialId is null");
        checkParam(acceptorFile, "acceptorFile is null");

        AppResult loadOpenAppFastResult = openAppService.loadOpenAppFast(senderAppId);
        if (!loadOpenAppFastResult.isSuccess()) {
            logger.error("failed to call openAppService.loadOpenAppFast. senderAppId[{}]" + senderAppId);
        }
        loadOpenAppFastResult.getResult().getAppCreater();

        Map<String, List<Integer>> acceptorMap = getAcceptorMap(acceptorFile);
        if (acceptorMap.size() < 1) {
            throw new BizException(AjaxCode.PARAM_ERROR, "未读到接收人");
        }
        logger.info("ea count ={}", acceptorMap.size());

        long sendCount = 0;
        long tempCount = 0;
        int sendThreshold = ConfigCenter.SEND_MESSAGE_MAX_THRESHOLD;
        int sendSleepTime = ConfigCenter.SEND_MESSAGE_MAX_THRESHOLD_SLEEP_TIME;
        for (Map.Entry<String, List<Integer>> entry : acceptorMap.entrySet()) {
            String fsEa = entry.getKey();
            List<Integer> employees = entry.getValue();
            MessageVO messageVO = new MessageVO();
            messageVO.setAppId(senderAppId);
            messageVO.setSender(new FsUserVO(senderFsEa, true));
            messageVO.setCreatorTypeEnum(CreatorTypeEnum.APP_ADMIN);
            messageVO.setMaterialType(MaterialTypeEnum.IMAGE_TEXT);
            messageVO.setMaterialId(materialId);
            AccepterVO accepterVO = new AccepterVO();
            accepterVO.setIsAllEmployees(false);
            accepterVO.setEa(fsEa);
            accepterVO.setEmployees(employees);
            messageVO.setAccepterVO(accepterVO);
            logger.info("sendMessage start. ea[{}], employees.size[{}], employees[{}]", fsEa, employees.size(), employees);
            BaseResult<String> sendMessageResult = materialMessageService.sendMessage(messageVO, MessageSendTypeEnum.SYSTEM_MSG);
            if (!sendMessageResult.isSuccess()) {
                logger.error("failed to call materialMessageService.sendMessage. messageVO[{}], messageSendTypeEnum[{}], sendMessageResult[{}]",
                        messageVO, MessageSendTypeEnum.SYSTEM_MSG, sendMessageResult);
                throw new BizException(AjaxCode.BIZ_EXCEPTION, "发送消息失败");
            }
            logger.info("sendMessage end. ");
            // 消息发送频次控制，根据企信的建议最好小于5000人/min
            sendCount += employees.size();
            tempCount += employees.size();
            logger.info("has sent count = {}", sendCount);
            if (tempCount >= sendThreshold) {
                logger.info("begin sleep. tempCount[{}], sendThreshold[{}], sendSleepTime[{}]", tempCount, sendThreshold, sendSleepTime);
                tempCount = 0;
                try {
                    Thread.sleep(sendSleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.info("send complete. sendCount[{}]", sendCount);
        return new AjaxResult(null);
    }

    private Map<String, List<Integer>> getAcceptorMap(@RequestParam(value = "acceptorFile", required = false) MultipartFile acceptorFile) {
        Map<String, List<Integer>> acceptorMap = new HashedMap();
        InputStream is = null;
        try {
            is = acceptorFile.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int line = 0;
            FsUserVO tempUser = null;
            long count = 0L;
            String s = bufferedReader.readLine();
            while (StringUtils.isNotBlank(s)) {
                line++;
                if (!FsUserVO.isFsUserString(s)) {
                    throw new BizException(AjaxCode.PARAM_ERROR, "第" + line + "行格式错误 [" + s + "]");
                }
                tempUser = new FsUserVO(s);
                if (CollectionUtils.isEmpty(acceptorMap.get(tempUser.getEnterpriseAccount()))) {
                    acceptorMap.put(tempUser.getEnterpriseAccount(), new ArrayList<>());
                }
                acceptorMap.get(tempUser.getEnterpriseAccount()).add(tempUser.getUserId());
                count++;
                s = bufferedReader.readLine();
            }
            logger.info("getAcceptorMap all acceptor count = {}", count);
        } catch (IOException e) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return acceptorMap;
    }

}
