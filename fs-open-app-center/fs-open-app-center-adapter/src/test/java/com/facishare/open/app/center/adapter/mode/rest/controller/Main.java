package com.facishare.open.app.center.adapter.mode.rest.controller;

import com.facishare.open.app.center.adapter.model.proto.QueryAppAdmins;
import com.facishare.open.app.center.adapter.model.proto.QueryQuota;
import com.facishare.open.app.center.adapter.model.proto.QueryUsers;
import com.facishare.open.app.center.adapter.model.proto.RemoveUsers;
import com.facishare.open.app.center.adapter.model.proto.SaveUsers;
import com.google.common.collect.Lists;
import com.google.common.io.ByteStreams;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.List;

/**
 * Created by xialf on 7/8/16.
 *
 * @author xialf
 */
public class Main {
    //    public static final String ADDR = "http://172.31.160.180:28292/CRM/queryQuota";
    public static final String HOST = "localhost:8080";
//    public static final String HOST = "172.31.160.180:28292";

    public static void main(String[] args) throws IOException {
        queryQuota("1204");
        saveUsers("1204", Lists.newArrayList(1000));
        queryUsers("1204");
        removeUsers("1204", Lists.newArrayList(1000));
        queryUsers("1204");
        queryAppAdmins("1204", "FSAID_5f5e227");
//        removeUsers("7", Lists.newArrayList(1081));
//        System.out.println(removeUsersResult);
    }


    public static void queryQuota(final String fsEa) throws IOException {
        final String url = "/CRM/queryQuota";

        final QueryQuota.Arg arg = new QueryQuota.Arg();
        arg.setFsEa(fsEa);
        final Schema<QueryQuota.Arg> schema = RuntimeSchema.getSchema(QueryQuota.Arg.class);
        final byte[] bytes = ProtobufIOUtil.toByteArray(arg, schema, LinkedBuffer.allocate(256));

        final byte[] resultBytes = post(url, bytes);
        final Schema<QueryQuota.Result> resultSchema = RuntimeSchema.getSchema(QueryQuota.Result.class);
        final QueryQuota.Result result = new QueryQuota.Result();
        ProtobufIOUtil.mergeFrom(resultBytes, result, resultSchema);
        System.out.println(result);
    }

    public static void queryUsers(final String fsEa) throws IOException {
        final String url = "/CRM/view/queryUsers";
        final QueryUsers.Arg arg = new QueryUsers.Arg();
        arg.setFsEa(fsEa);
        final Schema<QueryUsers.Arg> schema = RuntimeSchema.getSchema(QueryUsers.Arg.class);
        final byte[] bytes = ProtobufIOUtil.toByteArray(arg, schema, LinkedBuffer.allocate(256));

        final byte[] resultBytes = post(url, bytes);
        final Schema<QueryUsers.Result> resultSchema = RuntimeSchema.getSchema(QueryUsers.Result.class);
        final QueryUsers.Result result = new QueryUsers.Result();
        ProtobufIOUtil.mergeFrom(resultBytes, result, resultSchema);
        System.out.println(result);
    }

    public static void saveUsers(final String fsEa, List<Integer> userIds) throws IOException {
        final String url = "/CRM/view/saveUsers";

        final SaveUsers.Arg arg = new SaveUsers.Arg();
        arg.setFsEa(fsEa);
        arg.setUserIds(userIds);
        final Schema<SaveUsers.Arg> schema = RuntimeSchema.getSchema(SaveUsers.Arg.class);
        final byte[] bytes = ProtobufIOUtil.toByteArray(arg, schema, LinkedBuffer.allocate(256));
        final byte[] resultBytes = post(url, bytes);

        final Schema<SaveUsers.Result> resultSchema = RuntimeSchema.getSchema(SaveUsers.Result.class);

        final SaveUsers.Result result = new SaveUsers.Result();
        ProtobufIOUtil.mergeFrom(resultBytes, result, resultSchema);
        System.out.println(result);
    }

    public static void removeUsers(final String fsEa, List<Integer> userIds) throws IOException {
        final String url = "/CRM/view/removeUsers";

        final RemoveUsers.Arg arg = new RemoveUsers.Arg();
        arg.setFsEa(fsEa);
        arg.setUserIds(userIds);
        final Schema<RemoveUsers.Arg> schema = RuntimeSchema.getSchema(RemoveUsers.Arg.class);
        final byte[] bytes = ProtobufIOUtil.toByteArray(arg, schema, LinkedBuffer.allocate(256));

        final byte[] resultBytes = post(url, bytes);
        final Schema<RemoveUsers.Result> resultSchema = RuntimeSchema.getSchema(RemoveUsers.Result.class);
        final RemoveUsers.Result result = new RemoveUsers.Result();
        ProtobufIOUtil.mergeFrom(resultBytes, result, resultSchema);
        System.out.println(result);
    }

    public static void queryAppAdmins(final String fsEa, final String appId) throws IOException {
        final String url = "/app/queryAppAdmins";

        final QueryAppAdmins.Arg arg = new QueryAppAdmins.Arg();
        arg.setFsEa(fsEa);
        arg.setAppId(appId);

        final Schema<QueryAppAdmins.Arg> schema = RuntimeSchema.getSchema(QueryAppAdmins.Arg.class);
        final byte[] bytes = ProtobufIOUtil.toByteArray(arg, schema, LinkedBuffer.allocate(256));

        final byte[] resultBytes = post(url, bytes);
        final Schema<QueryAppAdmins.Result> resultSchema =
                RuntimeSchema.getSchema(QueryAppAdmins.Result.class);
        final QueryAppAdmins.Result result = new QueryAppAdmins.Result();
        ProtobufIOUtil.mergeFrom(resultBytes, result, resultSchema);
        System.out.println(result);
    }

    private static byte[] post(final String url, byte[] data) throws IOException {
        final CloseableHttpClient httpClient = HttpClients.custom().build();
        HttpPost httpPost = new HttpPost(getFullUrl(url));

        final ByteArrayEntity entity = new ByteArrayEntity(data);
        entity.setContentType("application/octet-stream");
        httpPost.setEntity(entity);

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            final StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() >= 400) {
                throw new IOException(statusLine.toString());
            }
            return ByteStreams.toByteArray(response.getEntity().getContent());
        }
    }

    private static String getFullUrl(final String relativePath) {
        return "http://" + HOST + relativePath;
    }
}
