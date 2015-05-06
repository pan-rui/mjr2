package com.p2p.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.p2p.service.BRoleService;

public class HttpClientUtil {
	private static Log log = LogFactory.getLog(HttpClientUtil.class);
	public static int HTTP_OK = 200;
	private final static X509TrustManager trustManager = new X509TrustManager() {
		public void checkClientTrusted(X509Certificate[] xcs, String string)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] xcs, String string)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

	};

	public static String doGet(String uri) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);
		try {
			return dealResponse(httpclient, httpGet);
		} catch (IOException e) {
			log.error(uri, e);
		} finally {
			HttpClientUtils.closeQuietly(httpclient);
		}
		return "";
	}

	private static String dealResponse(CloseableHttpClient httpclient,
			HttpUriRequest request) throws IOException {
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(request);
			if (HTTP_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity);
			}
		} catch (IOException e) {
			log.error(response, e);
		} finally {
			HttpClientUtils.closeQuietly(response);
		}
		return "";

	}

	public static String doPost(String url, Map<String, String> map,
			String charset) throws IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, map.get(key)));
		}
		if (charset == null || "".equals(charset)) {
			charset = "utf-8";
		}
		try {
			HttpPost httpPost = new HttpPost(url);

			httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));
			return dealResponse(httpclient, httpPost);
		} catch (UnsupportedEncodingException e) {
			log.error(nvps, e);
		} finally {
			HttpClientUtils.closeQuietly(httpclient);
		}
		return "";
	}

	public static String doPostHttps(String url, Map<String, String> map,
			String charset) throws IOException {
		CloseableHttpClient client = createSSLInsecureClient();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, map.get(key)));
		}
		if (charset == null || "".equals(charset)) {
			charset = "utf-8";
		}
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));
			return dealResponse(client, httpPost);

		} catch (IOException e) {
			log.error(nvps, e);
		} finally {
			HttpClientUtils.closeQuietly(client);
		}

		return "";
	}

	private static CloseableHttpClient createSSLInsecureClient() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					null, new TrustStrategy() {
						// 信任所有
						public boolean isTrusted(X509Certificate[] chain,
								String authType) throws CertificateException {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			log.error("访问失败KeyManagementException", e);
		} catch (NoSuchAlgorithmException e) {
			log.error("访问失败NoSuchAlgorithmException", e);
		} catch (KeyStoreException e) {
			log.error("访问失败KeyStoreException", e);
		}
		return HttpClients.createDefault();
	}

}
