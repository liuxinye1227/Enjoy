package com.facishare.open.app.center.api.utils;

import com.facishare.open.common.result.exception.BizException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.security.KeyManagementException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author chenzengyong
 * @date on 2016/12/12.
 */

public class HttpUploadUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUploadUtils.class);

    static PoolingHttpClientConnectionManager cm = null;
    private static SSLContext sslcontext = SSLContexts.createDefault();

    static {
        try {
            sslcontext.init(null, new X509TrustManager[]{new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] x509Certificates,
                                                       String s) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] x509Certificates,
                                                       String s) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }},
                    new SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        LayeredConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(500);
        cm.setDefaultMaxPerRoute(20);
    }

    private static CloseableHttpClient httpClient = HttpClients.custom()
            .setSSLContext(sslcontext)
            .setConnectionManager(cm)
            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();

    public static String httpUpload(String uploadImageUrl, byte[] data, String fileName) {
        HttpPost post = new HttpPost(uploadImageUrl);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addBinaryBody("uploadFile", data, ContentType.MULTIPART_FORM_DATA, fileName);
        HttpEntity entity = builder.build();
        post.setEntity(entity);
        HttpResponse response;
        InputStream content;
        try {
            response = httpClient.execute(post);
            content = response.getEntity().getContent();
        } catch (IOException e) {
            logger.error("httpClient.execute error ", e);
            throw new BizException(500, e, "upload image error");
        }
        String s;
        try {
            s = inputStream2Str(content, "");
        } catch (IOException e) {
            logger.error("inputStream2Str", e);
            throw new BizException(500, e, "inputStream2Str error");
        }

        return parseData(s).getData().getFileId();
    }

    private static ImageHttpVO parseData(String data) {
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        ImageHttpVO imageHttpVO = g.fromJson(data, new TypeToken<ImageHttpVO>() {
        }.getType());
        return imageHttpVO;
    }

    public static void main(String[] args) {
        String a = "{\"imageUrl\":\"sdfsdfsdf\",\"width\":200,\"fileId\":\"A_201612_13_0ce38b7e5b6746a3a3dc8442c940539d.jpg\",\"height\":200}";
        String b = "{\"errCode\":0,\"errMsg\":\"OK\",\"data\":{\"imageUrl\":\"https://open.ceshi113.com/fscdn/bj/imgTxtView?imgId\\u003dA_201612_13_8e62b2b1351b4fa1bedd87114166bbbf.jpg\\u0026s\\u003d670\",\"width\":200,\"fileId\":\"A_201612_13_8e62b2b1351b4fa1bedd87114166bbbf.jpg\",\"height\":200}}";
//        String a = "{\"imageUrl\":\"sjdlfkj\"}";
        System.out.println(a);
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        ImageHttpVO map = g.fromJson(b, new TypeToken<ImageHttpVO>() {
        }.getType());
//        Map<String, Object> map = parseData(a);
        System.out.println(b);
    }


    public static String inputStream2Str(InputStream in, String encode) throws IOException {

        String str;
        if (encode == null || encode.equals("")) {
            // 默认以utf-8形式
            encode = "utf-8";
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, encode));
        StringBuffer sb = new StringBuffer();

        while ((str = reader.readLine()) != null) {
            sb.append(str).append("\n");
        }
        return sb.toString();
    }

    /**
     * 用于http上传图片返回json解析
     *
     * @author chenzengyong
     * @date on 2016/12/13.
     */
    class ImageHttpVO {

        private int errCode;

        private String errMsg;

        private DataVO data;

        public int getErrCode() {
            return errCode;
        }

        public void setErrCode(int errCode) {
            this.errCode = errCode;
        }

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

        public DataVO getData() {
            return data;
        }

        public void setData(DataVO data) {
            this.data = data;
        }


        @Override
        public String toString() {
            return "imageAjaxVO{" +
                    "errCode=" + errCode +
                    ", errMsg='" + errMsg + '\'' +
                    ", data=" + data +
                    '}';
        }
    }

    /**
     * 用于http上传图片返回json解析
     *
     * @author chenzengyong
     * @date on 2016/12/13.
     */
    class DataVO implements Serializable {

        private String imageUrl;

        private String width;

        private String fileId;

        private String height;


        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        @Override
        public String toString() {
            return "DataVO{" +
                    "imageUrl='" + imageUrl + '\'' +
                    ", width='" + width + '\'' +
                    ", fileId='" + fileId + '\'' +
                    ", height='" + height + '\'' +
                    '}';
        }
    }


}
