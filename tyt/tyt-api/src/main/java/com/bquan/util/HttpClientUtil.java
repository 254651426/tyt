package com.bquan.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	
	public static String httpGet(String url, Map<String, String> params) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		Iterator<String> keysIte = params.keySet().iterator();
		int index = 0;
		while (keysIte.hasNext()) {
			String key = keysIte.next();
			if (index == 0) {
				url = url + "?" + key + "=" + params.get(key);
			} else {
				url = url + "&" + key + "=" + params.get(key);
			}
			index++;
		}
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse response = null;
		StringBuilder result = new StringBuilder();

		// 设置请求和传输超时时间5s,设置cookie策略
		RequestConfig requestconfig = RequestConfig.custom()
				.setSocketTimeout(5000).setConnectTimeout(5000)
				.setCookieSpec(CookieSpecs.BEST_MATCH).build();
		httpget.setConfig(requestconfig);

		try {
			response = httpClient.execute(httpget);
			HttpEntity entity = response != null ? response.getEntity() : null;
			if (entity != null) {
				InputStream in = entity.getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						in, "UTF-8"));
				String line;
				while ((line = br.readLine()) != null) {
					result.append(line);
					result.append("\n");
				}
				in.close();
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(response != null) response.close();
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}

	public static String httpPost(String url, String body) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		String result = null;

		try {
			StringEntity stringEntity = new StringEntity(body, "UTF-8");
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);
			HttpEntity entity = response != null ? response.getEntity() : null;

			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(response != null) response.close();
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static String httpPost(String url, Map<String, String> params) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		String result = null;

		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for(String key : params.keySet()){
			    nvps.add(new BasicNameValuePair(key, params.get(key)));  
			}
	        httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));  
			response = httpClient.execute(httpPost);
			HttpEntity entity = response != null ? response.getEntity() : null;

			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(response != null) response.close();
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String getBodyString(BufferedReader br) {
		String inputLine;
		String str = "";
		try {
			while ((inputLine = br.readLine()) != null) {
				str += inputLine;
			}
			br.close();
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
		return str;
	}
	
	/**
	 * 设置url以及键值对参数
	 * @param url
	 * @param params
	 * @return
	 */
	public static String getUrlWithParams(String url, Map<String,String> params) {
		Iterator<String> keysIte = params.keySet().iterator();
		int index = 0;
		while (keysIte.hasNext()) {
			String key = keysIte.next();
			if (index == 0) {
				url = url + "?" + key + "=" + params.get(key);
			} else {
				url = url + "&" + key + "=" + params.get(key);
			}
			index++;
		}
		return url;
	}
	
	/**
	 * 按照一定顺序设置url以及键值对参数
	 * @param url
	 * @param params
	 * @param orderList
	 * @return
	 */
	public static String getUrlWithParams(String url, Map<String,String> params, List<String> orderList) {
		int index = 0;
		for(String key : orderList){
			if (index == 0) {
				url = url + "?" + key + "=" + params.get(key);
			} else {
				url = url + "&" + key + "=" + params.get(key);
			}
			index++;
		}
		return url;
	}
}
