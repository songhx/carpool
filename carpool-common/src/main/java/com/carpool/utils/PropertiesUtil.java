package com.carpool.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
	private volatile static PropertiesUtil newInstance = null;
	private static Map<String,Object> proptisMap ;

	private static final Properties prop = new Properties();

	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	@SuppressWarnings("unchecked")
	public static Map<String,String> getInstance() throws Exception{
		if(newInstance == null){
			synchronized(PropertiesUtil.class){
				if(newInstance == null){
					newInstance = new PropertiesUtil();
				}
			}
		}
		return (Map<String,String>)proptisMap.get("/dbConfig.properties");
	}
	/**
	 * 根据路径加载指定路径的属性文件
	 * @param path properties file path
	 * @return Properties object
	 */
	public static Properties getPropertiesInstance(String path){
		if (newInstance == null) {
			synchronized (PropertiesUtil.class){
				if (newInstance == null) {
					try {
						newInstance = new PropertiesUtil(path);
					} catch (Exception e) {
						logger.error("Instance PropertiesUtils class exception");
					}
				}
			}
		}else {
			newInstance.loadPropertiesFile(path);
		}
		return prop;
	}

	public static void loadPropertiesFile(String path){
		InputStream inputStream = null;
		try {
			inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(path);
			prop.load(inputStream);
		} catch (IOException e) {
			logger.error("Load Properties file encountered an exception {} ",path);
		}finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("Close InputStream exception at PropertiesUtils class ");
				}
			}
		}
	}


	@SuppressWarnings("unchecked")
	public static Map<String,String> getInstance(String path) throws Exception{
		if(newInstance == null){
			synchronized(PropertiesUtil.class){
				if(newInstance == null){
					newInstance = new PropertiesUtil(path);
				}
			}
		} else {
			if(newInstance.getProptisMap().get(path) == null){
				synchronized(PropertiesUtil.class){
					if(newInstance.getProptisMap().get(path) == null){
						newInstance.loadProperties(path);
					}
				}
			}
		}

		return (Map<String,String>)proptisMap.get(path);
	}

	private PropertiesUtil() throws Exception{
		this.loadProperties("/dbConfig.properties");
	}

	private PropertiesUtil(String path) throws Exception{
		this.loadProperties(path);
	}

	public static Properties readProperties(String path){
		Properties properties = new Properties();
		try {
			ClassLoader classLoader = PropertiesUtil.class.getClass().getClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream(path);
			properties.load(inputStream);
		} catch (IOException e) {
			logger.error("load properties file {} failure ! " ,path);
		}
		return properties;
	}

	public void loadProperties(String path) throws Exception{
		Properties prop =  new  Properties();
		InputStream in = getClass().getClassLoader().getResourceAsStream(path);
		Map<String,String> map = new HashMap<String,String>();
		try{
			prop.load(in);
			@SuppressWarnings("rawtypes")
			Enumeration en = prop.keys();
			while(en.hasMoreElements()){
				String name = en.nextElement().toString();
				String value = prop.getProperty(name).trim();
				map.put(name, value);
			}
			in.close();

			if(proptisMap == null || proptisMap.isEmpty()){
				proptisMap = new HashMap<String,Object>();
			}
			proptisMap.put(path, map);
		} catch(IOException e){
			throw new Exception();
		}
	}

	private Map<String, Object> getProptisMap() {
		return proptisMap;
	}

	@SuppressWarnings({ "static-access", "unused" })
	private void setProptisMap(Map<String, Object> proptisMap) {
		this.proptisMap = proptisMap;
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			Map<String,String> proptis = PropertiesUtil.getInstance("redis.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
