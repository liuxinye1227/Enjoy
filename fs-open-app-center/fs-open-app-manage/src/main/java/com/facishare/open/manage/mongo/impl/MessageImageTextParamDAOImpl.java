package com.facishare.open.manage.mongo.impl;

import com.facishare.open.manage.mongo.MessageImageTextParamDAO;
import com.facishare.open.manage.model.MessageImageTextParamDO;
import com.facishare.open.material.api.enums.MessageStatusEnum;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liqiulin on 2017/2/6.
 */
@Repository
public class MessageImageTextParamDAOImpl implements MessageImageTextParamDAO {
    @Resource(name = "materialDataStore")
    private Datastore datastore;

    @Override
    public List<String> queryImageTextParamIds(List<String> messageIds, long startTime, long endTime) {
        Query query = datastore.createQuery(MessageImageTextParamDO.class);
        query.criteria("status").equal(MessageStatusEnum.NORMAL.getCode());
        query.criteria("createTime").greaterThanOrEq(startTime);
        query.criteria("createTime").lessThanOrEq(endTime);
        query.criteria("messageId").in(messageIds);
        query.retrievedFields(true, "imageTextParamId");
        List<MessageImageTextParamDO> result = query.asList();
        return result.stream().map(paramDO -> paramDO.getImageTextParamId()).collect(Collectors.toList());
    }

}
