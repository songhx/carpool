package com.carpool.utils;

/**
 * 读取配置文件config.properties文件信息
 *
 */

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {
	 private static Properties properties=null;
	  private static Logger log = Logger.getLogger(ReadConfig.class);
	  public ReadConfig(String configName){
		  properties=getConfig( this.getClass().getResourceAsStream(configName));
	  }
	 
	  /**
	   * 根据配置属名称取属性
	   * @param configName String
	   * @return String
	   */
	  public String getConfigByName(String configName){
	    return properties.getProperty(configName);
	  }
	  
	  public synchronized static Properties getConfig(InputStream configName) {
			if (properties == null) {
				properties = new Properties();
				InputStream is = null;
				try {
					is = configName ;
					properties.load(is);
				} catch (Exception e) {
					log.info("配置文件解析异常",e) ;
					throw new RuntimeException("failed to read config.properties file");
				} finally {
					if (is != null) {
						try {
							is.close();
						} catch (IOException e) {
							if (log.isDebugEnabled()) {
								log.debug(e.getMessage());
							}
						}
					}
				}
			}
			return properties;
		}
}
