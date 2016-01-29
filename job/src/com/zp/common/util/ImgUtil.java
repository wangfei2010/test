package com.zp.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ImgUtil {
	/**
	 * 单个文件上传
	 * @param is
	 * @param fileName
	 * @param filePath
	 */
		public static void upFile(File uploadFile,String fileName,String filePath){
			
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			FileInputStream is = null;
			BufferedInputStream bis = null;
			File file = new File(filePath);
			if(!file.exists()){
				file.mkdirs();
			}
			File f = new File(filePath+"/"+fileName);
			try {
				is = new FileInputStream(uploadFile);
				bis = new BufferedInputStream(is);
				fos = new FileOutputStream(f);
				bos = new BufferedOutputStream(fos);
				byte[] bt = new byte[4096];
				int len = 0;
				while((len = bis.read(bt))>0){
					bos.write(bt, 0, len);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				
					try {
						if(null != bos){
						bos.close();
						bos = null;
						}
						if(null != fos){
							fos.close();
							fos= null;
						}
						if(null != is){
							is.close();
							is=null;
						}
						
						if (null != bis) {
							bis.close();
							bis = null;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		
		/**
		 * http网络文件下载
		 * 
		 */
		
		public static void saveToFile(String destUrl, String fileName) throws IOException {  
	        FileOutputStream fos = null;  
	        BufferedInputStream bis = null;  
	        HttpURLConnection httpUrl = null;  
	        URL url = null;  
	        byte[] buf = new byte[1024];  
	        int size = 0;  
	        // 建立链接  
	        url = new URL(destUrl);  
	        httpUrl = (HttpURLConnection) url.openConnection();  
	        // 连接指定的资源  
	        httpUrl.connect();  
	        // 获取网络输入流  
	        bis = new BufferedInputStream(httpUrl.getInputStream());  
	        // 建立文件  
	        fos = new FileOutputStream(fileName);  
//	        if (this.DEBUG)  
//	            System.out.println("正在获取链接[" + destUrl + "]的内容.../n将其保存为文件["  
//	                    + fileName + "]");  
	       System.out.println(new File("E:\\imgs\\"+fileName).getAbsolutePath());
//	        // 保存文件  
	        while ((size = bis.read(buf)) != -1)  
	            fos.write(buf, 0, size);  
	        fos.close();  
	        bis.close();  
	        httpUrl.disconnect();  
		}  

		
		/**
		 * 文件下载
		 * @param response
		 * @param downloadFile
		 */
		public static void downloadFile(String downloadFile, String fileName) {
			byte[] bt = new byte[1024];
			try {
				FileOutputStream fs = new FileOutputStream(fileName);
				URL url = new java.net.URL(downloadFile);
				try {
					InputStream inputStream = url.openStream();
					int read = inputStream.read(bt);
					while(read != -1){
						fs.write(bt,0,read);
						read = inputStream.read(bt);
					}
					fs.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				
			}
			
		}
		
		/**
		 * 文件下载
		 * @param response
		 * @param downloadFile
		 */
		public static void downloadFile(HttpServletResponse response, String downloadFile, String showFileName) {
			
			BufferedInputStream bis = null;
			InputStream is = null;
			ServletOutputStream os = null;
			BufferedOutputStream bos = null;
			try {
				File file=new File(downloadFile); //:文件的声明
		        String fileName=file.getName();
		        is = new FileInputStream(file);  //:文件流的声明
		        PrintWriter out =  response.getWriter(); 
		        os = response.getOutputStream(); // 重点突出
		        bis = new BufferedInputStream(is);
		        bos = new BufferedOutputStream(os);
		        // 对文件名进行编码处理中文问题
		        fileName = java.net.URLEncoder.encode(showFileName, "UTF-8");// 处理中文文件名的问题
		        fileName = new String(fileName.getBytes("UTF-8"), "GBK");// 处理中文文件名的问题
		        response.reset(); // 重点突出
		        response.setCharacterEncoding("UTF-8"); // 重点突出
		        response.setContentType("application/x-msdownload");// 不同类型的文件对应不同的MIME类型 // 重点突出
		        // inline在浏览器中直接显示，不提示用户下载
		        // attachment弹出对话框，提示用户进行下载保存本地
		        // 默认为inline方式
		        response.setHeader("Content-Disposition", "attachment; filename="+fileName); // 重点突出
		        int bytesRead = 0;
		        byte[] buffer = new byte[1024];
		        while ((bytesRead = bis.read(buffer)) != -1){ //重点
		            bos.write(buffer, 0, bytesRead);// 将文件发送到客户端
		        }
		        
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			} finally {
				// 特别重要
		        // 1. 进行关闭是为了释放资源
		        // 2. 进行关闭会自动执行flush方法清空缓冲区内容
				try {
					if (null != bis) {
						bis.close();
						bis = null;
					}
					if (null != bos) {
						bos.close();
						bos = null;
					}
					if (null != is) {
						is.close();
						is = null;
					}
					if (null != os) {
						os.close();
						os = null;
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}
			}
		}
	    
}
