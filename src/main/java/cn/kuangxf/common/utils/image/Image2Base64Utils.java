package cn.kuangxf.common.utils.image;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.util.Base64;

/**
 * 图片进行base64编码、解码
 *
 */
public class Image2Base64Utils {

	/**
	 * 图片转换成字符串<br/>
	 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * 
	 * @param imgFile
	 *            图片路径
	 * @return
	 */
	public static String image2Str(String imgFile) {//
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		return Base64.encodeBase64String(data);// 返回Base64编码过的字节数组字符串
	}

	/**
	 * base64字符串转化成图片<br/>
	 * 对字节数组字符串进行Base64解码并生成图片
	 * 
	 * @param imgStr
	 *            图片的base64编码
	 * @param imgFilePath
	 *            存储路径
	 * @return
	 */
	public static boolean str2Image(String imgStr, String imgFilePath) { //
		if (imgStr == null) {// 图像数据为空
			return false;
		}
		try {
			// Base64解码
			byte[] b = Base64.decodeBase64(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
