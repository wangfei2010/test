package wf.com.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 文件工具类
 * @author WangFei
 */
public class FileUtil {
	public static boolean isExist(String filePath){
		if(StringUtils.isEmpty(filePath)) return false;
		
		File file = new File(filePath);
		
		return file.exists();
	}
	public static boolean writeFile(InputStream inputStream, String filePath) throws IOException {
		if ((inputStream == null) || StringUtils.isEmpty(filePath)) {
			return false;
		}

		File file = new File(filePath.replace("\\", "/").replace("//", "/"));
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (!parent.exists()) {
				// 新建文件夹
				if (!parent.mkdirs()) {
					return false;
				}
			}
			// 创建新文件
			if (!file.createNewFile()) {
				return false;
			}
		}

		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file);
			// 写入流
			return writeStream(inputStream, outputStream);
		}
		finally {
			if (outputStream != null) {
				outputStream.close();
			}
			// 释放输入流
			inputStream.close();
		}
	}

	/**
	 * 删除文件
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath.replace("\\", "/").replace("//", "/"));
		if (!file.exists()) {
			return true;
		}

		return deleteFile(file);
	}

	/**
	 * 删除文件
	 * @param file
	 * @return
	 */
	public static boolean deleteFile(File file) {
		if (file == null) return false;

		if (!file.exists()) return false;

		if (file.isDirectory()) return false;

		return file.delete();
	}

	/**
	 * 写入文件流
	 * @param inputStream
	 * @param outputStream
	 * @return
	 * @throws IOException
	 */
	public static boolean writeStream(InputStream inputStream, OutputStream outputStream) throws IOException {
		if ((inputStream == null) || (outputStream == null)) {
			return false;
		}

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(inputStream);
			bos = new BufferedOutputStream(outputStream);

			byte[] buffer = new byte[102400];
			int len;

			while ((len = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, len);
			}
		}
		finally {
			if (bis != null) {
				bis.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
		return true;
	}

	public static String getFileSize(long size) {
		String fileSize;
		if (size > 1024 * 1024 * 1024) {
			fileSize = new BigDecimal(size).divide(new BigDecimal(1024 * 1024 * 1024), 2, RoundingMode.HALF_DOWN).toString() + "G";
		}
		else if (size > 1024 * 1024) {
			fileSize = new BigDecimal(size).divide(new BigDecimal(1024 * 1024), 2, RoundingMode.HALF_DOWN).toString() + "MB";
		}
		else if (size > 1024) {
			fileSize = new BigDecimal(size).divide(new BigDecimal(1024), 2, RoundingMode.HALF_DOWN).toString() + "KB";
		}
		else {
			fileSize = String.valueOf(size) + "B";
		}
		return fileSize;
	}
}
