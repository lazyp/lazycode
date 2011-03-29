package cdu.computer.hxl.util;

import java.io.InputStream;
import java.net.URL;

/**
 * 获得资源
 * 
 * @author hxl
 * 
 */
public final class Resource {

	/**
	 * 
	 * @param relativePath
	 *            相对src的一个路径
	 * @return 资源流
	 */
	public static InputStream getResourceStream(String relativePath) {
		return Resource.class.getClassLoader()
				.getResourceAsStream(relativePath);
	}

	/**
	 * 
	 * @param relativePath
	 *            相对src的一个路径
	 * @return 资源的一个URL
	 */
	public static URL getResourceURL(String relativePath) {
		return Resource.class.getClassLoader().getResource(relativePath);

	}

	public static void main(String[] args) {
		System.out.println(Resource.getResourceURL("images/trayIcon.jpg"));
	}
}
