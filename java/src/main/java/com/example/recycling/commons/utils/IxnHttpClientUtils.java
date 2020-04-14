package com.example.recycling.commons.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 基于httpclient 4.5封装的工具类
 */
public class IxnHttpClientUtils {

    private static final Logger logger = LoggerFactory.getLogger(IxnHttpClientUtils.class);

    /**
     * get请求，请自行拼接好url，设置超时时间
     *
     * @param url
     *            请求的url
     * @param timeout
     *            超时，单位毫秒
     * @return
     */
    public static String requestByGet(String url, int timeout) {
        logger.info("get url:{}", url);
        HttpGet httpget = new HttpGet(url);
        Builder builder = RequestConfig.custom();
        // 获取链接的时间
        builder.setConnectionRequestTimeout(timeout)
                // 链接时间，从共享池获取链接
                .setConnectTimeout(timeout)
                // 读取数据时间
                .setSocketTimeout(timeout);
        httpget.setConfig(builder.build());
        String string = "";
        try (CloseableHttpClient httpclient = HttpClients.createDefault();
             CloseableHttpResponse response = httpclient.execute(httpget)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                string = EntityUtils.toString(entity, "utf-8");
            }
        } catch (ClientProtocolException e) {
            logger.error("httpclient异常", e);
        } catch (IOException e) {
            logger.error("httpclient异常", e);
        }
        logger.info("get result:{}", string);
        return string;
    }

    /**
     * post请求，设置超时时间
     *
     * @param url
     *            请求的url
     * @param params
     *            参数，封装在map
     * @param timeout
     *            超时，单位毫秒
     * @return
     */
    public static String requestByPostForm(String url, Map<String, String> params, int timeout) {
        logger.info("post url:{}", url);
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        if (null != params) {
            Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> next = iterator.next();
                list.add(new BasicNameValuePair(next.getKey(), next.getValue()));
                logger.info("post param-{}:{}", next.getKey(), next.getValue());
            }
        }
        // url格式编码
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
            httpPost.setEntity(uefEntity);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        Builder builder = RequestConfig.custom();
        // 获取链接的时间
        builder.setConnectionRequestTimeout(timeout)
                // 链接时间，从共享池获取链接
                .setConnectTimeout(timeout)
                // 读取数据时间
                .setSocketTimeout(timeout);
        httpPost.setConfig(builder.build());
        String string = "";
        try (CloseableHttpClient httpclient = HttpClients.createDefault();
             CloseableHttpResponse response = httpclient.execute(httpPost)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                string = EntityUtils.toString(entity, "utf-8");
            }
        } catch (ClientProtocolException e) {
            logger.error("httpclient异常", e);
        } catch (IOException e) {
            logger.error("httpclient异常", e);
        }
        logger.info("post result:{}", string);
        return string;
    }

    /**
     * psot json
     *
     * @param url
     * @param json
     * @param timeout
     *            超时，单位毫秒
     * @return
     */
    public static String requestByPostJson(String url, String json, int timeout) {
        logger.info("post url:{};post json:{}", url, json);
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json, "utf-8");
        // url格式编码
        httpPost.setEntity(entity);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        Builder builder = RequestConfig.custom();
        // 获取链接的时间 单位毫秒
        builder.setConnectionRequestTimeout(timeout)
                // 链接时间，从共享池获取链接
                .setConnectTimeout(timeout)
                // 读取数据时间
                .setSocketTimeout(timeout);
        httpPost.setConfig(builder.build());
        String string = "";
        try (CloseableHttpClient httpclient = HttpClients.createDefault();
             CloseableHttpResponse response = httpclient.execute(httpPost)) {
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                string = EntityUtils.toString(responseEntity, "utf-8");
            }
        } catch (ClientProtocolException e) {
            logger.error("httpclient异常", e);
        } catch (IOException e) {
            logger.error("httpclient异常", e);
        }
        logger.info("post result:{}", string);
        return string;
    }

    /**
     * post xml
     *
     * @param url
     * @param xml
     * @param timeout
     *            超时，单位毫秒
     * @return
     */
    public static String requestByPostXml(String url, String xml, int timeout) {
        logger.info("post url:{};post xml:{}", url, xml);
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(xml, "utf-8");
        // url格式编码
        httpPost.setEntity(entity);
        httpPost.addHeader("Content-Type", "text/xml;charset=utf-8");
        Builder builder = RequestConfig.custom();
        // 获取链接的时间 单位毫秒
        builder.setConnectionRequestTimeout(timeout)
                // 链接时间，从共享池获取链接
                .setConnectTimeout(timeout)
                // 读取数据时间
                .setSocketTimeout(timeout);
        httpPost.setConfig(builder.build());
        String string = "";
        try (CloseableHttpClient httpclient = HttpClients.createDefault();
             CloseableHttpResponse response = httpclient.execute(httpPost)) {
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                string = EntityUtils.toString(responseEntity, "utf-8");
            }
        } catch (ClientProtocolException e) {
            logger.error("httpclient异常", e);
        } catch (IOException e) {
            logger.error("httpclient异常", e);
        }
        logger.info("post result:{}", string);
        return string;
    }

    public static String requestByPostMethodNew(String url, List<NameValuePair> list) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost post = new HttpPost(url);
            if (list!=null) {
                UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
                post.setEntity(uefEntity);
            }
            logger.info("POST 请求...." + post.getURI());
            Builder builder = RequestConfig.custom();
            // 获取链接的时间 单位毫秒
            builder.setConnectionRequestTimeout(1000*10)
                    // 链接时间，从共享池获取链接
                    .setConnectTimeout(1000*10)
                    // 读取数据时间
                    .setSocketTimeout(1000*60);
            post.setConfig(builder.build());
            // 执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            try {
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    String response = EntityUtils.toString(entity, "UTF-8");// 在response关闭之前，消费掉，否则数据量过大entity会受影响，只会保留一小部分缓存内容
                    return response;
                }
            } finally {
                httpResponse.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String url = "http://aishuidai.ishuirong.com/hfcreditFeedback/loanAmountDetail";
        String json = "{\"name\":\"value\"}";
        int timeout = 1000 * 5;
        String request = requestByPostJson(url, json, timeout);
        System.out.println(request);
    }
    /**
     * @param urlAll
     *            :请求接口
     * @param httpArg
     *            :参数
     * @return 返回结果
     */
    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?key=" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("POST");
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

