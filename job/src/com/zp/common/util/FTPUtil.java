package com.zp.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import org.apache.commons.net.ftp.FTPClient;


public class FTPUtil {
	    /**
	     * 直接写入FTP
	     * @param fileContent
	     * @param server
	     * @param userName
	     * @param userPassword
	     * @param path
	     * @param fileName
	     * @param prot
	     * @return
	     */
	    public static boolean uploadFileByApacheByBinary(String fileContent,String account,
	    		 String path, String fileName) { 
	           FTPClient ftpClient = new FTPClient(); 
	             try { 
	            	 if(account != null){
	 	        		String[] ftpAccount = account.split(" ");
	 	        		String ftpIP = ftpAccount[0];
	 	        		int prot = Integer.parseInt(ftpAccount[1]);
	 	        		String ftpName = ftpAccount[2];
	 	        		String ftpPwd = ftpAccount[3];
	 	        		ftpClient.connect(ftpIP,prot); 
	 	        		ftpClient.login(ftpName, ftpPwd); 
	 	        	}
	            	 
	            	 InputStream is = new ByteArrayInputStream(fileContent.getBytes()); 
	                 ftpClient.changeWorkingDirectory(path); 
	                 ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
	                 ftpClient.enterLocalPassiveMode();
	                 ftpClient.storeFile(new String(fileName.getBytes("UTF-8"), "iso-8859-1") , is); 
	                 is.close(); 
	             } catch (Exception e) { 
	                    e.printStackTrace(); 
	                    return false; 
	             } finally { 
	                    if(ftpClient.isConnected()) { 
	                    	try { 
	                            ftpClient.disconnect(); 
	                        } catch (Exception e) { 
	                            e.printStackTrace(); 
	                        } 
	                    } 
	               } 
	        return true; 
	    }
	   /**
	    * 删除FTP文件
	    * @param fileName
	    * @param account
	    */
	    public static void deleteFtpFile(String fileName,String account){
	    	FTPClient ftpClient = new FTPClient(); 
	    	try {
	    		if(account != null){
	        		String[] ftpAccount = account.split(" ");
	        		String ftpIP = ftpAccount[0];
	        		int prot = Integer.parseInt(ftpAccount[1]);
	        		String ftpName = ftpAccount[2];
	        		String ftpPwd = ftpAccount[3];
	        		ftpClient.connect(ftpIP,prot); 
	        		ftpClient.login(ftpName, ftpPwd); 
	        	}
				ftpClient.enterLocalPassiveMode();
				ftpClient.deleteFile(fileName);
				ftpClient.isConnected();
			} catch (Exception e) {
				e.printStackTrace();
			} finally { 
	            try { 
	                ftpClient.disconnect(); 
	            } catch (IOException e) { 
	                e.printStackTrace(); 
	            } 
	        }  
	    	
	    }
	    
	    /**
	     * FTP查找文件目录
	     * @param ftpdirectory 文件目录
	     * @return list
	     * @author zp_WY
	     * @throws IOException 
	     * @throws SocketException 
	     * @date  2015-1-12
	     */
	    public static List getfilenamelist(String ftpdirectory,String account) {
	    	FTPClient ftpClient = new FTPClient(); 
	    	List list = new ArrayList();
	    	try{
	    		if(account != null){
	        		String[] ftpAccount = account.split(" ");
	        		String ftpIP = ftpAccount[0];
	        		int prot = Integer.parseInt(ftpAccount[1]);
	        		String ftpName = ftpAccount[2];
	        		String ftpPwd = ftpAccount[3];
	        		ftpClient.connect(ftpIP,prot); 
	        		ftpClient.login(ftpName, ftpPwd); 
	        	}
	            ftpClient.enterLocalPassiveMode();
	            String[] listFiles = ftpClient.listNames(ftpdirectory);
	            String filename="";
	            for(int i=0;i<listFiles.length;i++){
	            	filename = listFiles[i];
	            	list.add(filename);
	            }
	    	}
	    	catch (IOException e){
	    		e.printStackTrace();
	    	}finally{
	    		try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    	return list;
	    }
	    
	    /**
	     * 直接读取文件
	     * @param fileName
	     * @param account
	     * @return
	     */
	    public static String readFtpFile(String fileName,String account){
	    	FTPClient ftp = new FTPClient();
	    	StringBuilder builder = null;
			try {
				if(account != null){
	        		String[] ftpAccount = account.split(" ");
	        		String ftpIP = ftpAccount[0];
	        		int prot = Integer.parseInt(ftpAccount[1]);
	        		String ftpName = ftpAccount[2];
	        		String ftpPwd = ftpAccount[3];
	        		ftp.connect(ftpIP,prot); 
	        		ftp.login(ftpName, ftpPwd); 
	        	}
				ftp.enterLocalPassiveMode();
				InputStream ins = ftp.retrieveFileStream(fileName);
	        	BufferedReader reader = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
				String line;
				builder = new StringBuilder(150);
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				reader.close();
				if (ins != null) {
					ins.close();
				}
				// 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
				ftp.getReply();
		        
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
	    		try {
					ftp.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    	
	    	return builder.toString();
	    	
	    }
 public static String getDateTime(int i){
	 		
	 		Date date = new Date();
	 		if(i==0){
	 			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	 			return sd.format(date);//报文时间 
	 		}else{
	 			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
	 			String messageType = CustomPro.getValue("MessageType");
	 			return messageType + sdf.format(date) + (int)(Math.random()*10000);
	 		}
		}    
	    
}
