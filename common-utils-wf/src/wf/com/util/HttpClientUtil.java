package wf.com.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import org.apache.log4j.Logger;

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
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
	}

	// 允许获取httpClient，从而可以修改默认连接参数
	public HttpClient getHttpClient() {
		return httpClient;
	}

	/**
	 * 执行GET方法
	 * @param url 请求地址
	 * @param paramaters 请求参数
	 * @return 请求结果
	 * @throws IOException
	 */
	public String executeGet(String url, Map<String, String> paramaters) throws IOException {
		String result = null;

		HttpState state = new HttpState();
		// 创建GET方法的实例
		GetMethod getMethod = new GetMethod(url);

		// 封装参数
		if (null != paramaters) {
			if (null != paramaters.get("server_account_username") && null != paramaters.get("server_account_passowrd")) {
				UsernamePasswordCredentials creds = new UsernamePasswordCredentials((String) paramaters.get("server_account_username"), (String) paramaters.get("server_account_passowrd"));
				state.setCredentials(AuthScope.ANY, creds);
			}
			NameValuePair[] params = new NameValuePair[paramaters.size()];
			Iterator<Entry<String, String>> iter = paramaters.entrySet().iterator();
			int index = 0;

			while (iter.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				NameValuePair pair = null;
				if (null != val) {
					pair = new NameValuePair(key.toString(), val.toString());
				}
				else {
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
			byte[] responseBody = new byte[1024]; // getMethod.getResponseBody();
			InputStream resBody = getMethod.getResponseBodyAsStream();
			StringBuffer sb = new StringBuffer();
			int len = -1;
			while ((len = resBody.read(responseBody)) != -1) {
				sb.append(new String(responseBody, 0, len, "utf-8"));
			}
			// 处理内容
			result = sb.toString();
		}
		finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return result;
	}

	public String executePost(String url, Map<String, String> paramaters) throws IOException {
		return executePost(url, paramaters, "utf-8");
	}

	/**
	 * 执行POST方法
	 * @param url 服务器地址
	 * @param paramaters 参数
	 * @param returnStrCharacter 返回数据 
	 * @return
	 * @throws IOException
	 */
	public String executePost(String url, Map<String, String> paramaters, String returnStrCharacter) throws IOException {
		String result = null;

		HttpState state = new HttpState();
		if (null != paramaters.get("server_account_username") && null != paramaters.get("server_account_passowrd")) {
			UsernamePasswordCredentials creds = new UsernamePasswordCredentials((String) paramaters.get("server_account_username"), (String) paramaters.get("server_account_passowrd"));
			state.setCredentials(AuthScope.ANY, creds);
		}
		// 创建GET方法的实例
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		Iterator<Entry<String, String>> iter = paramaters.entrySet().iterator();

		while (iter.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			if (null != val) {
				postMethod.setParameter(key.toString(), val.toString());
			}
		}
		try {
			// 执行getMethod
			httpClient.executeMethod(null, postMethod, state);

			byte[] responseBody = new byte[1024]; // getMethod.getResponseBody();
			InputStream resBody = postMethod.getResponseBodyAsStream();
			StringBuffer sb = new StringBuffer();
			int len = -1;
			while ((len = resBody.read(responseBody)) != -1) {
				sb.append(new String(responseBody, 0, len, returnStrCharacter));
			}
			// 处理内容
			result = sb.toString();
		}
		finally {
			// 释放连接
			postMethod.releaseConnection();
		}
		return result;
	}

	/**
	 * 上传文件到服务器
	 * @param url 服务器地址
	 * @param params 参数
	 * @param fileParamName 
	 * @param filePath 文件路径
	 * @param fileType 文件类型
	 * @param outArg
	 * @return
	 */
	public int uploadFile(String url, Map<String, String> params, String fileParamName, String filePath, String fileType, StringBuffer outArg) {
		PostMethod post = new PostMethod(url);
		try {

			// 要上传的文件
			File file = new File(filePath);
			if (!file.exists()) {
				return 2;
			}
			// 参数
			List<Part> partList = new ArrayList<Part>();
			FilePart filePart = new FilePart(fileParamName, file);
			if (null != fileType && fileType.length() > 0) filePart.setContentType(fileType);
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
			post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			// 发送HTTP上传请求
			int status = client.executeMethod(post);
			if (status == HttpStatus.SC_OK) {
				// 上传成功
				byte[] responseBody = post.getResponseBody();
				// 处理内容
				outArg.append(new String(responseBody, "utf-8"));
				return 1;
			}
			else {
				return status;
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
		finally {
			// 释放连接
			post.releaseConnection();
		}
	}

}
