package cn.kuangxf.common.utils.http;
import java.math.BigDecimal;

public class GPSUtils {
	private static double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 取得2点之间的距离
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return 2点之间订单的距离（M）
	 */
	public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 1000);
		return s;
	}

	/**
	 * 米转换成千米（保留1位小数）
	 * 
	 * @param m
	 * @return
	 */
	public static double m2km(double m) {
		BigDecimal r = new BigDecimal(m);
		//r = r.multiply(new BigDecimal(10));
		r = r.divide(new BigDecimal(1000), 1, BigDecimal.ROUND_HALF_UP);
		return r.doubleValue();
	}

	public static void main(String[] args) {
		/*double lat1 = 106.474988;
		double lng1 = 29.559751;
		double lat2 = 106.50492;
		double lng2 = 29.541213;*/
		
		System.out.println(GPSUtils.m2km(1000.0));

	}
}
