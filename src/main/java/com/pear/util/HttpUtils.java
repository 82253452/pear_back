package com.pear.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import java.io.EOFException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by admin on 2016/12/24.
 */
public class HttpUtils {
    private static Logger logger = Logger.getLogger(HttpUtils.class);

    public static String get(String url) {
        CloseableHttpClient client = createSSLClientDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(2000).setConnectTimeout(2000)
                .setSocketTimeout(2000).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        try {
            CloseableHttpResponse httpResponse = client.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
        } catch (ConnectTimeoutException e) {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("链接超时 位置名称：" + url);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
            logger.error("连接超时 位置：" + url);
        } catch (SocketTimeoutException e) {
            System.out.println("》》》》》》》》》》》》》》》》》》》》》》》》》》》");
            System.out.println("网络异常 位置名称：" + url);
            System.out.println("》》》》》》》》》》》》》》》》》》》》》》》》》》》");
            logger.error("网络异常 位置名称：" + url);

        } catch (UnrecognizedPropertyException e) {
            System.out.println("-------------------------------------------------");
            System.out.println("转换json数据失败的位置：" + url);
            System.out.println("-------------------------------------------------");
            logger.error("转换json数据失败的位置：" + url);
        } catch (EOFException e) {
            System.out.println("**************************************************");
            System.out.println("EOFException异常 位置名称：" + url);
            System.out.println("**************************************************");
            logger.error("EOFException异常:" + e);
        } catch (IOException e) {
            System.out.println("《《《《《《《《《《《《《《《《《《《《《《《《《《《");
            System.out.println("IO异常 位置名称：" + url);
            System.out.println("《《《《《《《《《《《《《《《《《《《《《《《《《《《");
            logger.error("IO异常:" + e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String post(String url, JSONObject json) {
        CloseableHttpClient client = createSSLClientDefault();
        String param = "?";
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(200).setConnectTimeout(200)
                .setSocketTimeout(200).build();
        for (String key : json.keySet()) {
            try {
                param += key + "=" + URLEncoder.encode(json.getString(key) == null ? "" : json.getString(key), "GBK") + "&";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (!param.equals("?")) {
            param = param.substring(0, param.length() - 1);
        } else {
            param = "";
        }
        HttpPost httpPost = new HttpPost(url + param);
        httpPost.setConfig(requestConfig);
        try {
            CloseableHttpResponse httpResponse = client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
        } catch (ConnectTimeoutException e) {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("连接超时 位置名称：" + url);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
            logger.error("连接超时 位置：" + url);
        } catch (SocketTimeoutException e) {
            System.out.println("》》》》》》》》》》》》》》》》》》》》》》》》》》》");
            System.out.println("网络异常 位置名称：" + url);
            System.out.println("》》》》》》》》》》》》》》》》》》》》》》》》》》》");
            logger.error("网络异常 位置名称：" + url);

        } catch (UnrecognizedPropertyException e) {
            System.out.println("-------------------------------------------------");
            System.out.println("转换json数据失败的位置：" + url);
            System.out.println("-------------------------------------------------");
            logger.error("转换json数据失败的位置：" + url);
        } catch (EOFException e) {
            System.out.println("**************************************************");
            System.out.println("EOFException异常 位置名称：" + url);
            System.out.println("**************************************************");
            logger.error("EOFException异常:" + e);
        } catch (IOException e) {
            System.out.println("《《《《《《《《《《《《《《《《《《《《《《《《《《《");
            System.out.println("IO异常 位置名称：" + url);
            System.out.println("《《《《《《《《《《《《《《《《《《《《《《《《《《《");
            logger.error("IO异常:" + e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static CloseableHttpClient createSSLClientDefault() {
        try {
            javax.net.ssl.SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
                    null, new TrustStrategy() {
                        // 信任所有
                        public boolean isTrusted(X509Certificate[] chain,
                                                 String authType) throws CertificateException {
                            return true;
                        }
                    }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }
}
