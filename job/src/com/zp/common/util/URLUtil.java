package com.zp.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class URLUtil {
	
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            if(param==null||"".equals(param)){
            	urlNameString = url;
            }
            // 空格替换
            urlNameString = urlNameString.replace(" ", "%20");
            URL realUrl = new URL(urlNameString);
            System.out.println("url====" + urlNameString);
            
           
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            /*
             * Map<String, List<String>> map = connection.getHeaderFields(); //
             * 遍历所有的响应头字段 for (String key : map.keySet()) {
             * System.out.println(key + "--->" + map.get(key)); }
             */
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println("result====" + result);
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    
    /**
     * 向指定 URL 发送POST方法的请求 
     * @param urlStr 请求地址+key+token+format
     * @param xmlInfo  Response Message Body
     * @return json数据
     */
    public static String sendPost(String urlStr,Object xmlInfo) {
    	StringBuffer bf=new StringBuffer();
    	HttpURLConnection con =null;
    	BufferedReader br = null;
        try {  
            URL url = new URL(urlStr);  
//          URLConnection con = url.openConnection(); 
            con = (HttpURLConnection) url.openConnection();  
            con.setDoOutput(true);  
            con.setRequestMethod("POST");
            con.setRequestProperty("Pragma:", "no-cache");  
            con.setRequestProperty("Cache-Control", "no-cache");  
            con.setRequestProperty("Content-Type", "text/xml");
            con.addRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
            con.connect();
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(),"UTF-8");   
            
            out.write(new String(xmlInfo.toString().getBytes()));
            out.flush();  
            out.close();  
            String responseBody = null;
//            int respCode = con.getResponseCode();
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
//			if (HttpURLConnection.HTTP_OK == respCode) {
//				String readLine = null;
//				StringBuffer response = new StringBuffer();
//				while (null != (readLine = br.readLine())) {
//					response.append(readLine);
//				}
//				responseBody = response.toString();
//			}
//            System.out.println("----------------------responseBody:"+responseBody);
            String line = ""; 
            for (line = br.readLine(); line != null; line = br.readLine()) {  
            	if(line!=null){
            		bf.append(line);
            	}
            }  
            
//        }catch (IOException ex) { 
//        	res = (HttpWebResponse)ex.Response; 
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
     // 使用finally块来关闭输入流
        finally {
            try {
            	if (br != null) {
                	br.close();
                }
                if (con != null) {
                	con.disconnect();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return bf.toString();
    } 
    
    public static String sendPost2(String url, String params) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
        	 String urlNameString = url + "?" + params;
             if(params==null||"".equals(params)){
             	urlNameString = url;
             }
             System.out.println("url====" + urlNameString);
             // 空格替换
            urlNameString = urlNameString.replace(" ", "");
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            ((HttpURLConnection) conn).setRequestMethod("POST"); 
            conn.setRequestProperty("accept", "*/*");
            conn.setUseCaches(false); 
            
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.addRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
 
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(params);
            // flush输出流的缓冲
            out.flush();
 
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("========"+result);
        return result;
    }
    
    
}