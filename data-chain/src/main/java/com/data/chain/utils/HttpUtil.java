package com.data.chain.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.NoArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @version 1.0
 * @author Zpl
 * @date 2021/4/8 14:40 
 */
@NoArgsConstructor(staticName = "create")
public class HttpUtil {
    private HttpUriRequest httpUriRequest;
    private final Map<String, String> headers = new HashMap<>();

    public HttpUtil createGet(String url) {
        this.httpUriRequest = new HttpGet(url);
        httpUriRequest.setHeader("Content-Type","application/json;charset=utf-8");
        return this;
    }

    public HttpUtil createPost(String url, JSONObject body) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity bodyEntity = new StringEntity(body.toString(),StandardCharsets.UTF_8);
        httpPost.setEntity(bodyEntity);
        this.httpUriRequest = httpPost;
        this.httpUriRequest.setHeader("Content-Type","application/json;charset=utf-8");
        return this;
    }
    public HttpUtil createPost(String url, String body) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity bodyEntity = new StringEntity(body,StandardCharsets.UTF_8);
        httpPost.setEntity(bodyEntity);
        this.httpUriRequest = httpPost;
        this.httpUriRequest.setHeader("Content-Type","application/json;charset=utf-8");
        return this;
    }

    public HttpUtil addHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public HttpUtil addHeaders(Map<String ,String> param) {
        this.headers.putAll(param);
        return this;
    }

    public HttpResponse execute() {
        HttpResponse response;
        try{
            CloseableHttpClient httpClient = HttpClients.createDefault();
            this.headers.forEach((key, value) -> this.httpUriRequest.addHeader(key, value));
            response = httpClient.execute(this.httpUriRequest);
        } catch (Exception e){
            System.out.println("请求异常");
            throw new RuntimeException(e);
        } finally{
            this.httpUriRequest.abort();
        }
        return response;
    }

    public static String getData(HttpResponse response) {
        String result = null;
        Assert.notNull(response, "系统错误");
        try {
            InputStream in = response.getEntity().getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line = br.readLine())!=null){
                stringBuilder .append(line).append('\n');
            }
            br.close();
            in.close();
            result = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
