package com.zp.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class HttpClientUtil {

	private static final HttpClientUtil instance = new HttpClientUtil();

	private static Logger logger = Logger.getLogger(HttpClientUtil.class);

	public static HttpClientUtil getInstance() {
		return instance;
	}

	private static HttpClient httpClient;


	private HttpClientUtil() {
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

		connectionManager.getParams().setDefaultMaxConnectionsPerHost(300);
		connectionManager.getParams().setMaxTotalConnections(50000);

		httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
	}

	// 允许获取httpClient，从而可以修改默认连接参数
	public HttpClient getHttpClient() {
		return httpClient;
	}

	public String executeGet(String url, Map<String, String> paramaters)
			throws IOException {
		String result = null;

		HttpState state = new HttpState();
		// 创建GET方法的实例
		GetMethod getMethod = new GetMethod(url);

		// 封装参数
		if (null != paramaters) {
			if (null != paramaters.get("server_account_username")
					&& null != paramaters.get("server_account_passowrd")) {
				UsernamePasswordCredentials creds = new UsernamePasswordCredentials(
						paramaters.get("server_account_username"),
						paramaters.get("server_account_passowrd"));
				state.setCredentials(AuthScope.ANY, creds);
			}
			NameValuePair[] params = new NameValuePair[paramaters.size()];
			Iterator<Entry<String, String>> iter = paramaters.entrySet()
					.iterator();
			int index = 0;

			while (iter.hasNext()) {
				Map.Entry<String, String> entry = iter
						.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				NameValuePair pair = null;
				if (null != val) {
					pair = new NameValuePair(key.toString(), val.toString());
				} else {
					pair = new NameValuePair(key.toString(), null);
				}
				params[index++] = pair;

			}
			getMethod.setQueryString(params);
		}

		try {
			// 执行getMethod
			httpClient.executeMethod(null, getMethod, state);

			// 读取内容
			byte[] responseBody = getMethod.getResponseBody();
			// 处理内容
			result = new String(responseBody, "utf-8");

		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return result;
	}

	public String executePost(String url, Map<String, String> paramaters)
			throws IOException {
		String result = null;

		HttpState state = new HttpState();
		if (null != paramaters.get("server_account_username")
				&& null != paramaters.get("server_account_passowrd")) {
			UsernamePasswordCredentials creds = new UsernamePasswordCredentials(
					paramaters.get("server_account_username"),
					paramaters.get("server_account_passowrd"));
			state.setCredentials(AuthScope.ANY, creds);
		}
		// 创建GET方法的实例
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		Iterator<Entry<String, String>> iter = paramaters.entrySet().iterator();

		while (iter.hasNext()) {
			Map.Entry<String, String> entry = iter
					.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			if (null != val) {
				postMethod.setParameter(key.toString(), val.toString());
			}
		}

		try {
			// 执行getMethod
			httpClient.executeMethod(null, postMethod, state);

			byte[] responseBody = postMethod.getResponseBody();
			// 处理内容
			result = new String(responseBody, "utf-8");

		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
		return result;
	}

	public String executePost(String url, JSONObject jsonParam) throws  IOException {
		CloseableHttpClient closeableHttpClient=HttpClients.createDefault();
		String result = null;
		HttpPost httpPost=new HttpPost(url);
		StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题    
        entity.setContentEncoding("UTF-8");    
        entity.setContentType("application/json"); 
        httpPost.setEntity(entity);
		try {
			// 执行getMethod
			CloseableHttpResponse response = closeableHttpClient.execute(httpPost);

			HttpEntity responseentity = response.getEntity();
			// 处理内容
			result = EntityUtils.toString(responseentity, "UTF-8");

		} finally {
			// 释放连接
			closeableHttpClient.close();
		}
		return result;
	}

	public int uploadFile(String url, Map<String, String> params,
			String fileParamName, String filePath, String fileType,
			StringBuffer outArg) {
		try {
			PostMethod post = new PostMethod(url);
			// 要上传的文件
			File file = new File(filePath);
			if (!file.exists()) {
				return 2;
			}
			// 参数
			List<Part> partList = new ArrayList<Part>();
			FilePart filePart = new FilePart(fileParamName, file);
			if (null != fileType && fileType.length() > 0)
				filePart.setContentType(fileType);
			partList.add(filePart);
			if (params != null) {
				for (String key : params.keySet()) {
					partList.add(new StringPart(key, params.get(key)));
					post.setParameter(key, params.get(key));
				}
			}
			// 参数装成数组
			Part[] parts = new Part[partList.size()];
			for (int i = 0; i < partList.size(); i++) {
				parts[i] = partList.get(i);
			}
			// 设置发送的内容
			post.setRequestEntity(new MultipartRequestEntity(parts, post
					.getParams()));
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams()
					.setConnectionTimeout(5000);
			// 发送HTTP上传请求
			int status = client.executeMethod(post);
			if (status == HttpStatus.SC_OK) {
				// 上传成功
				byte[] responseBody = post.getResponseBody();
				// 处理内容
				outArg.append(new String(responseBody, "utf-8"));
				return 1;
			} else {
				return status;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}

}
