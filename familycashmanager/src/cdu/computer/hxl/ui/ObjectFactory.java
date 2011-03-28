package cdu.computer.hxl.ui;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import test.TabComponentsDemo;

import cdu.computer.hxl.util.Constants;

/**
 * IOC对象工厂
 * 
 * @author hxl
 * 
 */
public final class ObjectFactory {

	private static final String SPRING_CONFIG = "/spring.xml";
	private static XmlBeanFactory xmlBeanFactory = null;
	private static FileSystemResource resouce = null;
	
	/*
	 * 初始化对象工厂
	 */
	static {
		resouce = new FileSystemResource(Constants.BASE_PATH + SPRING_CONFIG);
		xmlBeanFactory = new XmlBeanFactory(resouce);
	}

	/**
	 * 根据bean的 name获得对象
	 * 
	 * @param beanName
	 * @return Object
	 */
	public static Object getInstance(String beanName) {
              
		return xmlBeanFactory.getBean(beanName);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TabComponentsDemo demo = (TabComponentsDemo) ObjectFactory
				.getInstance("tabDemo");
		demo.runTest();

	}

}
