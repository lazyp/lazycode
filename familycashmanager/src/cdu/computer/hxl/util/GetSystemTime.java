package cdu.computer.hxl.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetSystemTime {
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-mm-dd hh:MM:ss");

	public static String get() {
		return sdf.format(new Date());
	}
}
