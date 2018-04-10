package cn.kuangxf.common.utils.text;

/**
 * 随机字符串工具类
 * 
 * @author kuangxf
 *
 */
public class StringRandomUtil {

	final static String arrays = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	final static String arrays_ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 随机生成char
	 * 
	 * @return
	 */
	public static char randomChar() {
		Double random = Math.random();
		random = random * 100;
		int index = Math.abs(random.intValue());
		index = index % arrays.length();
		return arrays.charAt(index);
	}

	/**
	 * 随机生成char
	 * 
	 * @return
	 */
	public static char randomLetter() {
		Double random = Math.random();
		random = random * 100;
		int index = Math.abs(random.intValue());
		index = index % arrays_.length();
		return arrays_.charAt(index);
	}

	/**
	 * 数据生成字符串
	 * 
	 * @param length
	 *            字符串长度
	 * @return
	 */
	public static String randomString(int length) {
		char[] result = new char[length];
		for (int i = 0; i < length; i++) {
			result[i] = randomChar();
		}
		return new String(result);
	}

	/**
	 * 数据生成字符串
	 * 
	 * @param length
	 *            字符串长度
	 * @return
	 */
	public static String randomStringOnlyLetter(int length) {
		char[] result = new char[length];
		for (int i = 0; i < length; i++) {
			result[i] = randomLetter();
		}
		return new String(result);
	}

	/**
	 * @param length
	 *            控制生产的位数
	 * @return 生成纯数字
	 */
	public static String randomNumber(int length) {
		String number = "";
		for (int i = 0; i < length; i++) {
			int numval = (int) (Math.random() * 10);
			number += String.valueOf(numval);
		}
		return number;
	}

}
