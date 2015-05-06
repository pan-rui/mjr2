package com.p2p.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * FTP 文件上传管理类
 */
public class FTPManager {

	/**
	 * 向FTP服务器下载文件
	 * 
	 * @param path
	 *            FTP服务器保存目录
	 * @param files
	 *            上传的文件列表
	 * @return true 上传成功.false 上传失败
	 */
	public static boolean upLoad(Collection<File> files) {

		boolean isSuccess = false;
		FTPClient ftpClient = null;
		FileInputStream fis = null;
		try {
			ftpClient = connection();
			// 转到指定上传目录
			ftpClient.changeWorkingDirectory(FTPServer.getValue("ftp.path"));
			ftpClient.setBufferSize(1024 * 10); // 10M文件
			ftpClient.setControlEncoding("UTF-8");
			int fileSize = 0;
			// 设置文件类型（二进制）
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			for (File file : files) {
				fileSize += file.length(); // 计算文件大小
				fis = new FileInputStream(file);
				ftpClient.storeFile(file.getName(), fis);
			}

			// 关闭输入流
			fis.close();
			// 退出ftpClient
			ftpClient.logout();
			// 表示上传成功
			isSuccess = true;
			// System.out.println("上传完毕...");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("FTP客户端出错！", e);
		} finally {

			IOUtils.closeQuietly(fis);
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					throw new RuntimeException("关闭FTP连接发生异常！", e);
				}
			}
		}
		return isSuccess;
	}

	/**
	 * Description: 从FTP服务器下载文件
	 * 
	 * @param fileName
	 *            在文件服务器中要下载的文件名
	 * @param newName
	 *            下载到系统运行服务器后的文件名.
	 * @param savePath
	 *            下载后保存到系统运行服务的路径 (一般指定为当前系统跟路径下的指定文件夹下,比如 WEB-INF/download)
	 * @return true 下载成功. false 下载失败.
	 */
	public static boolean downLoad(String fileName, String newName,
			String savePath) {
		// 初始表示下载失败
		boolean success = false;
		// 创建FTPClient对象
		FTPClient ftpClient = null;
		try {
			ftpClient = connection();
			// 转到文件服务器中指定下载目录
			ftpClient.changeWorkingDirectory(FTPServer.getValue("ftp.path"));
			// 列出该目录下所有文件
			FTPFile[] fs = ftpClient.listFiles();
			// 遍历所有文件，找到指定的文件
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					// 根据绝对路径初始化文件

					File oldfile = new File(savePath + "/" + ff.getName()); // 文件服务器中的文件名
					File newfile = new File(savePath + "/" + newName); // 下载到运行服务器中的文件名

					oldfile.renameTo(newfile);
					// 输出流
					OutputStream is = new FileOutputStream(newfile);
					// 下载文件
					ftpClient.retrieveFile(ff.getName(), is);
					is.close();
				}
			}
			// 退出ftp
			ftpClient.logout();
			// 下载成功
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}

	private static FTPClient connection() {
		FTPClient ftpClient = new FTPClient();
		try {
			// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			if (StringUtils.isNotBlank(FTPServer.getValue("ftp.port"))) {
				ftpClient.connect(FTPServer.getValue("ftp.url"));
			} else {
				ftpClient.connect(FTPServer.getValue("ftp.url"),
						Integer.parseInt(FTPServer.getValue("ftp.port")));
			}
			// 登录ftp
			ftpClient.login(FTPServer.getValue("ftp.username"),
					FTPServer.getValue("ftp.password"));
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				System.out.println("FTP SERVER LOGIN SUCCESS ....");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ftpClient;
	}

	/**
	 * 转换文件大小
	 * 
	 * @param files
	 * @return
	 */
	public static String formetFileSize(long files) {
		DecimalFormat df = new DecimalFormat("0.00");
		String fileSizeString = "";

		if (files < 1024) {
			fileSizeString = df.format((double) files) + "B";
		} else if (files < 1048576) {
			fileSizeString = df.format((double) files / 1024) + "K";
		} else if (files < 1073741824) {
			fileSizeString = df.format((double) files / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) files / 1073741824) + "G";
		}
		return fileSizeString;
	}
}
