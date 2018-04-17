package cn.kuangxf.common.utils.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletResponse;

/**
 * Http与Servlet工具类.
 */
public class ServletUtils {

	/**
	 * 将字符串渲染到客户端
	 * 
	 * @param response
	 *            渲染对象
	 * @param string
	 *            待渲染的字符串
	 * @return null
	 */
	public static String renderString(ServletResponse response, String string) {
		return renderString(response, string, null);
	}

	/**
	 * 将字符串渲染到客户端
	 * 
	 * @param response
	 *            渲染对象
	 * @param string
	 *            待渲染的字符串
	 * @return null
	 */
	public static String renderString(ServletResponse response, String string, String type) {
		try {
			// response.reset(); // 先注释掉，否则以前设置的Header会被清理掉，如ajax登录设置记住我Cookie
			response.setContentType(type == null ? "application/json" : type);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(string);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
