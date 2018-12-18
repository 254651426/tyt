package com.bquan.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class HttpTools {



    private String apiUrl;
    private HttpClient client = null;
    private StringEntity entity = null;
    private HttpResponse response = null;
    private String method = "";
    private String path = "";
    private String param = "";
    private String fragment;
    private int code;
    private String body;
    private String requestBody;
    private Map<String, String> headers = new HashMap<String, String>();

    private StringBuffer sb = new StringBuffer();

    public HttpTools(String apiUrl, String method) {

        this.apiUrl = apiUrl;
        this.method = method;
    }

    public String path(String path) {

        this.path = path.trim();
        return this.path;
    }

    public void param(String key, String value) {

        if (!StringUtils.isBlank(this.param)) {
            this.param += "&";
        }
        this.param += key.trim() + "=" + value.trim();
    }

    public String getParam() {
        return param;
    }

    public void fragment(String info) {

        sb.append(info);
    }

    public void headers(String key, String value) {

        this.headers.put(key.trim(), value.trim());
    }

    public void body(String requestBody) {

        this.requestBody = requestBody.trim();
    }

    public boolean sendRequest() {

        boolean flag = false;
        try {

            client = new DefaultHttpClient();

            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 600000);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 600000);

            if (!StringUtils.isBlank(path)) {
                apiUrl += path;
            }

            if (!StringUtils.isBlank(param)) {
                apiUrl += "?" + param;
            }

            if (!StringUtils.isBlank(fragment)) {
                apiUrl += "#" + fragment;
            }

            if (!StringUtils.isBlank(requestBody)) {
                entity = new StringEntity(requestBody, Charset.forName("UTF-8"));
            }
            if (method.equalsIgnoreCase("GET")) {
                HttpGet get = new HttpGet(apiUrl);
                if (!this.headers.isEmpty()) {
                    for (Map.Entry<String, String> header : headers.entrySet()) {
                        get.addHeader(header.getKey(), header.getValue());
                    }
                }
                response = client.execute(get);
            } else if (method.equalsIgnoreCase("POST")) {
                HttpPost post = new HttpPost(apiUrl);
                if (!this.headers.isEmpty()) {
                    for (Map.Entry<String, String> header : headers.entrySet()) {
                        post.addHeader(header.getKey(), header.getValue());
                    }
                }
                entity.setContentEncoding("UTF-8");
                // 发送Json格式的数据请求
                entity.setContentType("application/json");
                post.setEntity(entity);
                response = client.execute(post);
            } else if (method.equalsIgnoreCase("PUT")) {
                HttpPut put = new HttpPut(apiUrl);
                if (!this.headers.isEmpty()) {
                    for (Map.Entry<String, String> header : headers.entrySet()) {
                        put.addHeader(header.getKey(), header.getValue());
                    }
                }
                put.setEntity(entity);
                response = client.execute(put);
            } else if (method.equalsIgnoreCase("DELETE")) {
                HttpDelete delete = new HttpDelete(apiUrl);
                if (!this.headers.isEmpty()) {
                    for (Map.Entry<String, String> header : headers.entrySet()) {
                        delete.addHeader(header.getKey(), header.getValue());
                    }
                }
                response = client.execute(delete);

            }
            HttpEntity entity = response.getEntity();
            body = EntityUtils.toString(entity);
        } catch (Exception e) {
        }

        int code = response.getStatusLine().getStatusCode();
        this.code = code;
        if (response != null)
            flag = true;

        client.getConnectionManager().shutdown();

        return flag;
    }

    public String getResponseBody() {

        System.out.println("The response body is " + body);
        return body;
    }

    public int getResponseCode() {

        System.out.println("The response code is " + code);
        return code;
    }
}
