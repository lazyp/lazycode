package cdu.computer.hxl.util;

/**
 * 定义系统的常量
 * 
 * @author hxl
 * @date 2011-03-15
 * 
 */
public interface Constants {
	public static final String BASE_PATH = Constants.class.getResource("/")
			.getPath();
	public static final String DATABASE_PATH = BASE_PATH + "/database/";
	public static final String DATABASE_NAME = "cash";
}
