package com.facishare.open.app.center.adapter.mode.rest.controller;

import com.facishare.open.app.center.adapter.model.proto.IsAppAdmin;
import com.facishare.open.app.center.adapter.model.proto.ProtobufResult;
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
public class AppHttpTest {
    private static final int OPERATOR_ID = 1009;
    private static final String FS_EA = "53469";
    private static final String SERVER = "http://localhost:8080";
//    private static final String SERVER = "http://10.113.32.5:28292";

    @Test
    public void isAppAdmin_protobuf_Success() throws IOException {
        final IsAppAdmin.Arg arg = new IsAppAdmin.Arg();
        arg.setFsEa(FS_EA);
        arg.setUserId(OPERATOR_ID);
        arg.setAppId("FSAID_5f5e248");

        final IsAppAdmin.Result result = new IsAppAdmin.Result();
        HttpRequestHelper.request(SERVER + "/app/isAppAdmin", arg, result);
        Assert.assertEquals(result.getMsg(), ProtobufResult.OK_CODE, (int) result.getCode());
        System.out.println(result);
    }

}
