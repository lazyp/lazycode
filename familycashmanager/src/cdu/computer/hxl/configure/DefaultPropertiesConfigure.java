package cdu.computer.hxl.configure;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import cdu.computer.hxl.util.Constants;
import cdu.computer.hxl.util.Resource;

/**
 * 加载数据库配置信息
 * 
 * @author hxl
 * 
 */
public class DefaultPropertiesConfigure {
	private Properties pro = new Properties();
	private InputStream propertiesStream = Resource
			.getResourceStream(Constants.DEFALUT_PROPERTIES_NAME);

	public DefaultPropertiesConfigure() {
		loadProperties();
	}

	public DefaultPropertiesConfigure(String configname) {
		propertiesStream = Resource.getResourceStream(configname);
		loadProperties();
	}

	private void loadProperties() {
		try {
			pro.load(propertiesStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDriver() {
		return pro.getProperty("driver");
	}

	public String getConnectionURL() {
		return pro.getProperty("url");
	}

	public String getDBName() {
		return pro.getProperty("DBName");
	}

	public String getUsername() {
		return pro.getProperty("username");
	}

	public String getPassword() {
		return pro.getProperty("password");
	}

	public String getDialect() {
		return pro.getProperty("dialect");
	}
}
