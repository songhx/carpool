package com.carpool.utils.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

/**
 * 
 * 类简述
 * <p>
 * FTP工具类
 * </p>
 * 
 * @Copyright
 * @version 1.0
 * @CreateDate 2016年5月5日 下午7:42:43
 */
public class SftpUtil {
	private static Logger logger = Logger.getLogger(SftpUtil.class);
	private static ChannelSftp sftp = null;

	/**
	 * 连接sftp服务器
	 * 
	 * @param host   主机
	 * @param port        端口
	 * @param username        用户名
	 * @param password      密码
	 * @return
	 */
	public static ChannelSftp connect(String host, int port, String username,
			String password) {
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			Session sshSession = jsch.getSession(username, host, port);
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return sftp;
	}

	/**
	 * Disconnect with server
	 */
	public static void disconnect() {
		if (sftp != null) {
			try {
				Session s = sftp.getSession();
				s.disconnect();
				logger.info("关闭FTP");
			} catch (JSchException e) {
				logger.info("e");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param directory
	 *            上传的目录
	 * @param uploadFile
	 *            要上传的文件(目录+文件名)
	 * @param sftp
	 * @throws SftpException
	 */
	public static void sftpUploadFile(String directory, String uploadFile,
			ChannelSftp sftp) throws SftpException {
		FileInputStream uploadLoaclFis = null;
		try {
			logger.info(sftp.pwd());
			/*if (!(directory.equals(sftp.pwd()))) {
				sftp.cd(directory);
			}*/
			createDir(directory, sftp);
		} catch (Exception e) {
			logger.info(e);
			logger.info(directory);
			sftp.mkdir(directory);
			sftp.cd(directory);
			e.printStackTrace();
		}
		sftp.pwd();
		File file = new File(uploadFile);
		try {
			uploadLoaclFis = new FileInputStream(file);
			sftp.put(uploadLoaclFis, file.getName());
			uploadLoaclFis.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (uploadLoaclFis != null) {
				try {
					uploadLoaclFis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件(文件名称)
	 * @param saveFile
	 *            存在本地的路径(目录+文件名称)
	 * @param sftp
	 */
	public static void download(String directory, String downloadFile,
			String saveFile, ChannelSftp sftp) {
		FileOutputStream downLoadLocalFos = null;
		try {
			sftp.cd(directory);
			File file = new File(saveFile);
			downLoadLocalFos = new FileOutputStream(file);
			sftp.get(downloadFile, downLoadLocalFos);
			downLoadLocalFos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (downLoadLocalFos != null)
				try {
					downLoadLocalFos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param directory
	 *            要删除文件所在目录
	 * @param deleteFile
	 *            要删除的文件(文件名)
	 * @param sftp
	 */
	public static void delete(String directory, String deleteFile,
			ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 列出目录下的文件
	 * 
	 * @param directory
	 *            要列出的目录
	 * @param sftp
	 * @return
	 * @throws SftpException
	 */
	@SuppressWarnings("rawtypes")
	public static Vector listFiles(String directory, ChannelSftp sftp)
			throws SftpException {
		return sftp.ls(directory);
	}

	/** 
	  * 创建一个文件目录 
	  */  
	 public static void createDir(String createpath, ChannelSftp sftp) {  
	  try {  
	   if (isDirExist(createpath)) {  
	    sftp.cd(createpath);  
	   }  
	   String pathArry[] = createpath.split("/");  
	   StringBuffer filePath = new StringBuffer("/");  
	   for (String path : pathArry) {  
	    if (path.equals("")) {  
	     continue;  
	    }  
	    filePath.append(path + "/");  
	    if (isDirExist(filePath.toString())) {  
	     sftp.cd(filePath.toString());  
	    } else {  
	     // 建立目录  
	     sftp.mkdir(filePath.toString());  
	     // 进入并设置为当前目录  
	     sftp.cd(filePath.toString());  
	    }  
	   }  
	   sftp.cd(createpath);  
	  } catch (SftpException e) {  
		  logger.info("创建路径错误：" + createpath);  
	  }  
	 }  
	 
	 /** 
	  * 判断目录是否存在 
	  */  
	public static boolean isDirExist(String directory) {  
	  boolean isDirExistFlag = false;  
	  try {  
	   SftpATTRS sftpATTRS = sftp.lstat(directory);  
	   isDirExistFlag = true;  
	   return sftpATTRS.isDir();  
	  } catch (Exception e) {  
	   if (e.getMessage().toLowerCase().equals("no such file")) {  
	    isDirExistFlag = false;  
	   }  
	  }  
	  return isDirExistFlag;  
	 }  
	
	public static void main(String[] args) throws Exception {
		String host = "111.206.116.91";
		int port = 10022;
		String username = "root";
		String password = "song@95cg%*#";
		String directory = "/usr/local/nginx/virtual/web/wms/10001/medias"; ///usr/local/nginx/virtual/web/wms/template/10001/medias
		String uploadFile = "D:\\data2\\1.txt";
		ChannelSftp sftp = connect(host, port, username, password);
		sftpUploadFile(directory, uploadFile, sftp);
		// sf.download(directory, downloadFile, saveFile, sftp);
		// sf.delete(directory, deleteFile, sftp);
		try {
			disconnect();
			System.out.println("finished");
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
