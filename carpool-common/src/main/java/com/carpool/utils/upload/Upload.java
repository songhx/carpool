package com.carpool.utils.upload;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.carpool.utils.ReadConfig;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 类简述
 * <p>
 * 上传
 * </p>
 * 
 * @Company 动力威视
 * @Copyright
 * @author (shx@sxw100.com)
 * @version 1.0
 * @CreateDate 2016年5月30日 下午4:25:47
 */
public class Upload {
	private static final Logger logger = Logger.getLogger(Upload.class);
	public static final ReadConfig properties = new ReadConfig("/upload.properties");

	/**
	 * 
	 * @Description: 从ueditor的返回json中取出文件的文件名，然后ftp
	 * @param @param json
	 * @param @param rootPath
	 * @return void
	 * @throws
	 * @date 2015年7月21日
	 */
	public static void uploadImage(String json, String rootPath) {
		logger.info("进入FTP方法,json=" + json);
		JSONObject jsonObject = JSON.parseObject(json);
		String state = "";
		String fileName = "";
		File rootpath = new File(rootPath);
		String uploadpath = rootpath.getParent();
		String remotePath = properties.getConfigByName("remotePath");
		if (StringUtils.isEmpty(remotePath)) {
			logger.info("缺少参数");
			return;
		}
		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
		String nowday = df1.format(new Date());
		String remotedir = remotePath + "/image/" + nowday;
		try {
			jsonObject.get("state");
			state = (String) jsonObject.get("state");
			if ("SUCCESS".equals(state)) {
				fileName = (String) jsonObject.get("title");
				String sourceFile = uploadpath + File.separator + "upload"
						+ File.separator + "image" + File.separator + nowday
						+ File.separator + fileName;
				File file = new File(sourceFile);
				if (file.exists()) {
					ftpFile(sourceFile, remotedir);
				}
			}

		} catch (Exception e) {
		}
	}

	/**
	 * 
	 * @Description: 自动创建传入的目录
	 * @param @param json
	 * @param @param rootPath
	 * @return void
	 * @throws
	 * @date 2016年7月29日
	 */
	public static void autoCreateDirect(String sourceFile, String rootPath) {
		logger.info("进入FTP方法,sourceFile=" + sourceFile);
		String remotePath = "/usr/local/nginx/virtual/web/wms/template/";
		if (StringUtils.isEmpty(remotePath)) {
			logger.info("缺少参数");
			return;
		}
		String remotedir = remotePath + rootPath;
		try {
			ftpFile(sourceFile, remotedir);
		} catch (Exception e) {
		}
	}
	/**
	 * 
	 * @Description: FTP方法,参数为源文件（路径+文件名），目标目录
	 * @param @param sourceFile
	 * @param @param remotePath
	 * @return void
	 * @throws
	 * @date 2015年7月21日
	 */
	public static void ftpFile(String sourceFile, String remotePath) {
		logger.info("进入FTP上传文件的方法");
		logger.info("源文件为" + sourceFile + "目标目录为" + remotePath);
		String ip = properties.getConfigByName("ip");
		String port = properties.getConfigByName("port");
		String password = properties.getConfigByName("password");
		String username = properties.getConfigByName("username");
		if (StringUtils.isEmpty(ip) 
				|| StringUtils.isEmpty(port)
				|| StringUtils.isEmpty(password)
				|| StringUtils.isEmpty(username)) {
			logger.info(ip + port + username + password);
			logger.error("缺少参数，不能进行FTP上传");
			return;
		}
		ChannelSftp sftp = null;
		sftp = SftpUtil.connect(ip, Integer.parseInt(port), username, password);
		if (sftp == null) {
			logger.error("连接FTP失败");
			return;
		}
		try {
			//File file = new File(sourceFile);
			//if (file.exists()) {
				SftpUtil.sftpUploadFile(remotePath, sourceFile, sftp);
			//}
		} catch (SftpException e) {
			logger.info(e);
			e.printStackTrace();
		} finally {
			SftpUtil.disconnect();

		}
	}

	public static void main(String args[]) {
		File path = new File("d:/data/backup");
		String parent = path.getParent();
		logger.info(parent);
	}

}
