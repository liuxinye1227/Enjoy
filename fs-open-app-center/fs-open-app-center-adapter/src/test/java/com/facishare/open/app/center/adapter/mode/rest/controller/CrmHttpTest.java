package com.facishare.open.app.center.adapter.mode.rest.controller;

import com.facishare.open.app.center.adapter.model.proto.AddCrmAppAdmins;
import com.facishare.open.app.center.adapter.model.proto.ProtobufResult;
import com.facishare.open.app.center.adapter.model.proto.QueryCrmAppAdmins;
import com.facishare.open.app.center.adapter.model.proto.QueryCrmAvail;
import com.facishare.open.app.center.adapter.model.proto.QueryCrmEmployeeTrialInfos;
import com.facishare.open.app.center.adapter.model.proto.QueryCrmQuotaRecords;
import com.facishare.open.app.center.adapter.model.proto.QueryCrmStatus;
import com.facishare.open.app.center.adapter.model.proto.RemoveCrmAppAdmins;
import com.google.common.collect.Lists;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by xialf on 2017/02/24.
 *
 * @author xialf
 * @since 2017/02/24 11:15 AM
 */
@Ignore
public class CrmHttpTest {
    private static final int OPERATOR_ID = 1000;
    private static final String FS_EA = "53469";
    private static final String SERVER = "http://localhost:8080";
//    private static final String SERVER = "http://10.113.32.5:28292";

    @Test
    public void queryCrmAvailability_Success() throws IOException {
        final QueryCrmAvail.Arg arg = new QueryCrmAvail.Arg();
        arg.setFsEa(FS_EA);
        arg.setUserId(OPERATOR_ID);

        final QueryCrmAvail.Result result = new QueryCrmAvail.Result();
        HttpRequestHelper.request(SERVER + "/CRM/queryCrmAvailability", arg, result);
        Assert.assertEquals(0, (int) result.getCode());
        System.out.printf("QueryCrmAvailResult: %s%n", result);
    }

    @Test
    public void queryAppAdmin_protobuf_Success() throws IOException {
        final QueryCrmAppAdmins.Arg arg = new QueryCrmAppAdmins.Arg();
        arg.setFsEa(FS_EA);
        arg.setOperatorId(OPERATOR_ID);

        final QueryCrmAppAdmins.Result result = new QueryCrmAppAdmins.Result();

        HttpRequestHelper.request(SERVER + "/CRM/admin/query", arg, result);
        Assert.assertEquals(result.getMsg(), ProtobufResult.OK_CODE, (int) result.getCode());
        System.out.printf("appAdmins: %s%n", result.getAppAdmins());
    }

    @Test
    public void addAppAdmin_protobuf_Success() throws IOException {
        final AddCrmAppAdmins.Arg arg = new AddCrmAppAdmins.Arg();
        arg.setFsEa(FS_EA);
        arg.setOperatorId(OPERATOR_ID);
        arg.setAppAdmins(Lists.newArrayList(1, 2, 3));

        final AddCrmAppAdmins.Result result = new AddCrmAppAdmins.Result();

        HttpRequestHelper.request(SERVER + "/CRM/admin/add", arg, result);
        Assert.assertEquals(result.getMsg(), ProtobufResult.OK_CODE, (int) result.getCode());
    }

    @Test
    public void removeAppAdmin_protobuf_Success() throws IOException {
        final RemoveCrmAppAdmins.Arg arg = new RemoveCrmAppAdmins.Arg();
        arg.setFsEa(FS_EA);
        arg.setOperatorId(OPERATOR_ID);
        arg.setAppAdmins(Lists.newArrayList(1, 2, 3));

        final RemoveCrmAppAdmins.Result result = new RemoveCrmAppAdmins.Result();

        HttpRequestHelper.request(SERVER + "/CRM/admin/remove", arg, result);
        Assert.assertEquals(result.getMsg(), ProtobufResult.OK_CODE, (int) result.getCode());
    }

    @Test
    public void queryStatus_protobuf_Success() throws IOException {
        final QueryCrmStatus.Arg arg = new QueryCrmStatus.Arg();
        arg.setFsEa(FS_EA);

        final QueryCrmStatus.Result result = new QueryCrmStatus.Result();
        HttpRequestHelper.request(SERVER + "/CRM/queryStatus", arg, result);
        Assert.assertEquals(result.getMsg(), ProtobufResult.OK_CODE, (int) result.getCode());
    }

    @Test
    public void queryCrmQuotaRecords_protobuf_Success() throws IOException {
        final QueryCrmQuotaRecords.Arg arg = new QueryCrmQuotaRecords.Arg();
        arg.setFsEa(FS_EA);

        final QueryCrmQuotaRecords.Result result = new QueryCrmQuotaRecords.Result();
        HttpRequestHelper.request(SERVER + "/CRM/queryQuotaRecords", arg, result);
        Assert.assertEquals(result.getMsg(), ProtobufResult.OK_CODE, (int) result.getCode());
        System.out.println(result);
    }

    @Test
    public void queryEmpTrialInfos_protobuf_Success() throws IOException {
        final QueryCrmEmployeeTrialInfos.Arg arg = new QueryCrmEmployeeTrialInfos.Arg();
        arg.setFsEa(FS_EA);

        final QueryCrmEmployeeTrialInfos.Result result = new QueryCrmEmployeeTrialInfos.Result();
        HttpRequestHelper.request(SERVER + "/CRM/queryEmployeeTrialInfos", arg, result);
        Assert.assertEquals(result.getMsg(), ProtobufResult.OK_CODE, (int) result.getCode());
        System.out.println(result);
    }

    @Test
    public void test() {
        final QueryCrmAppAdmins.Result result = new QueryCrmAppAdmins.Result(Lists.newArrayList());
        final byte[] bytes = ProtobufIOUtil.toByteArray(result, RuntimeSchema.getSchema(QueryCrmAppAdmins.Result.class), LinkedBuffer.allocate(256));
        Assert.assertNotNull(result.getAppAdmins());

        final QueryCrmAppAdmins.Result resultFrom = new QueryCrmAppAdmins.Result();
        ProtobufIOUtil.mergeFrom(bytes, resultFrom, RuntimeSchema.getSchema(QueryCrmAppAdmins.Result.class));
        Assert.assertNotNull(resultFrom.getAppAdmins());
    }

}
