package cn.kuangxf.common.utils.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class HttpClientUtil {
	public static Logger log = Logger.getLogger(HttpClientUtil.class);

	private static HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

	public static String doPost(String url, Map<String, String> map, String charset) throws Exception {
		if (StringUtils.isEmpty(url)) {
			throw new Exception("URL参数不能为空！");
		}

		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = httpClientBuilder.build();
			httpPost = new HttpPost(url);
			// 设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> elem = (Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
				httpPost.setEntity(entity);
			}

			HttpResponse response = httpClient.execute(httpPost);

			if (response != null) {
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() != 200) {
					throw new Exception("接口调用失败，错误代码：" + statusLine.getStatusCode());
				}
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			log.error(ex);
			throw new Exception(ex.getMessage());
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
		return result;
	}

	public static String doPostXml(String url, String xmlBody, String charset) throws Exception {
		if (StringUtils.isEmpty(url)) {
			throw new Exception("URL参数不能为空！");
		}

		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = httpClientBuilder.build();
			httpPost = new HttpPost(url);
			httpPost.addHeader("Content-Type", "text/xml");
			// 设置参数
			StringEntity entity = new StringEntity(xmlBody, charset);
			httpPost.setEntity(entity);

			HttpResponse response = httpClient.execute(httpPost);

			if (response != null) {
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() != 200) {
					throw new Exception("接口调用失败，错误代码：" + statusLine.getStatusCode());
				}
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			log.error(ex);
			throw new Exception(ex.getMessage());
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
		return result;
	}

	public static String doPost(String url, Map<String, String> map, Map<String, String> header, String charset)
			throws Exception {
		if (StringUtils.isEmpty(url)) {
			throw new Exception("URL参数不能为空！");
		}

		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = httpClientBuilder.build();
			httpPost = new HttpPost(url);
			// 设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> elem = (Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
				httpPost.setEntity(entity);
			}
			httpPost.setHeaders(getHeader(header));
			HttpResponse response = httpClient.execute(httpPost);

			if (response != null) {
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() != 200) {
					throw new Exception("接口调用失败，错误代码：" + statusLine.getStatusCode());
				}
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			log.error(ex);
			throw new Exception(ex.getMessage());
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
		return result;
	}

	public static String doGet(String url, Map<String, String> map, String charset) throws Exception {
		if (StringUtils.isEmpty(url)) {
			throw new Exception("URL参数不能为空！");
		}

		CloseableHttpClient httpClient = null;
		HttpGet httpGet = null;
		String result = null;
		try {
			if (!url.endsWith("?")) {
				url = url + "?";
			}
			url = url + getUrl(map, charset);
			httpClient = httpClientBuilder.build();
			httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet);
			if (response != null) {
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() != 200) {
					throw new Exception("接口调用失败，错误代码：" + statusLine.getStatusCode());
				}

				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			log.error(ex);
			throw new Exception(ex.getMessage());
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
		return result;
	}

	private static Header[] getHeader(Map<String, String> headerMap) {
		if (headerMap == null || headerMap.keySet().size() == 0) {
			return null;
		}
		List<Header> headerList = new ArrayList<>();
		Set<String> keys = headerMap.keySet();
		for (String key : keys) {
			Header header = new BasicHeader(key, headerMap.get(key));
			headerList.add(header);
		}
		return headerList.toArray(new Header[headerList.size()]);
	}

	private static String getUrl(Map<String, String> map, String charset) {
		if (null == map || map.keySet().size() == 0) {
			return "";
		}
		StringBuffer url = new StringBuffer();
		Set<String> keys = map.keySet();
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			String key = it.next();
			if (map.containsKey(key)) {
				String val = map.get(key);
				String str = val != null ? val : "";
				try {
					str = URLEncoder.encode(str, charset);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				url.append(key).append("=").append(str).append("&");
			}
		}
		String strURL = url.toString();
		if (strURL.endsWith("&")) {
			strURL = strURL.substring(0, strURL.length() - 1);
		}
		return strURL;
	}
}
