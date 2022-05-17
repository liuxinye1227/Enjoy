package com.facishare.open.app.center.adapter.mode.rest.controller;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

/**
 * Created by xialf on 2017/03/15.
 *
 * @author xialf
 * @since 2017/03/15 4:33 PM
 */
public class HttpRequestHelper {
    static <R, T> void request(final String url, T t, R r) throws IOException {
        @SuppressWarnings("unchecked")
        final Schema<T> schema = RuntimeSchema.getSchema((Class<T>) t.getClass());
        final byte[] bytes = ProtobufIOUtil.toByteArray(t, schema, LinkedBuffer.allocate(256));

        final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        final DefaultHttpClient httpClient = new DefaultHttpClient();
        final HttpPost request = new HttpPost(url);
        request.addHeader("accept", "application/octet-stream");
        request.setEntity(new ByteArrayEntity(bytes));
        final CloseableHttpResponse response = httpClient.execute(request);
        byte[] buf = new byte[1024];
        final int count = response.getEntity().getContent().read(buf);

        @SuppressWarnings("unchecked")
        final Schema<R> schemaResult = RuntimeSchema.getSchema((Class<R>) r.getClass());
        ProtobufIOUtil.mergeFrom(buf, 0, count, r, schemaResult);
    }
}
