package com.facishare.open.manage.mongo.impl;

import com.facishare.open.manage.model.MessageDO;
import com.facishare.open.manage.mongo.MessageDAO;
import com.facishare.open.material.api.enums.MessageStatusEnum;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.aggregation.Accumulator;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.aggregation.Group;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liqiulin on 2017/2/6.
 */
@Repository
public class MessageDAOImpl implements MessageDAO {
    @Resource(name = "materialDataStore")
    private Datastore datastore;

    @Override
    public long countMessage(List<Integer> types, List<Integer> sendTypes, long startTime, long endTime) {
        Query query = datastore.createQuery(MessageDO.class);
        if (!CollectionUtils.isEmpty(types)) {
            query.criteria("type").in(types);
        }
        if (!CollectionUtils.isEmpty(sendTypes)) {
            query.criteria("sendType").in(sendTypes);
        }
        query.criteria("status").equal(MessageStatusEnum.NORMAL.getCode());
        query.criteria("createTime").greaterThanOrEq(startTime);
        query.criteria("createTime").lessThanOrEq(endTime);
        return query.countAll();
    }

    @Override
    public List<String> queryMessage(List<Integer> types, List<Integer> sendTypes, long startTime, long endTime) {
        Query query = datastore.createQuery(MessageDO.class);
        if (!CollectionUtils.isEmpty(types)) {
            query.criteria("type").in(types);
        }
        if (!CollectionUtils.isEmpty(sendTypes)) {
            query.criteria("sendType").in(sendTypes);
        }
        query.criteria("status").equal(MessageStatusEnum.NORMAL.getCode());
        query.criteria("createTime").greaterThanOrEq(startTime);
        query.criteria("createTime").lessThanOrEq(endTime);
        query.retrievedFields(true, "messageId");
        List<MessageDO> messageList = query.asList();
        return messageList.stream().map(messageDO -> messageDO.getMessageId()).collect(Collectors.toList());
    }

    @Override
    public long countMessageApp(List<Integer> types, List<Integer> sendTypes, long startTime, long endTime) {
        Query query = datastore.createQuery(MessageDO.class);
        if (!CollectionUtils.isEmpty(types)) {
            query.criteria("type").in(types);
        }
        if (!CollectionUtils.isEmpty(sendTypes)) {
            query.criteria("sendType").in(sendTypes);
        }
        query.criteria("status").equal(MessageStatusEnum.NORMAL.getCode());
        query.criteria("createTime").greaterThanOrEq(startTime);
        query.criteria("createTime").lessThanOrEq(endTime);

        AggregationPipeline pipeline = datastore.createAggregation(MessageDO.class);
        pipeline.match(query);
        pipeline.group("appId").group("id", Group.grouping("count", new Accumulator("$sum", 1)));
        Iterator<MessageDAOImpl.CountResult> countResultIterator = pipeline.aggregate(MessageDAOImpl.CountResult.class);
        if (countResultIterator.hasNext()) {
            return countResultIterator.next().getCount();
        } else {
            return 0L;
        }
    }

    static class CountResult {
        private String id;
        private long count;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "CountResult{" +
                    "id='" + id + '\'' +
                    ", count=" + count +
                    '}';
        }
    }
}
