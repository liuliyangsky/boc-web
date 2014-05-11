package com.cherrypicks.boc.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.cherrypicks.boc.common.util.PropertiesUtil;

/**
 * http request common class
 * 
 * @author edwin.zhou
 * 
 */
public class HttpClientUtils {

	private static String DEFAULT_CHARSET = "UTF-8";
	
	private static String APPLICATION_JSON = "application/json;charset=utf-8";

	private static final int CONNECTION_TIMEOUT = NumberUtils.toInt(PropertiesUtil.getProperty("CONNECTION.TIMEOUT"), 5000);
	private static final int SO_TIMEOUT = NumberUtils.toInt(PropertiesUtil.getProperty("SO.TIMEOUT"), 10000);

	public static String getMethodRequest(String url) throws Exception {

		StringBuilder result = new StringBuilder();

		HttpParams httpParams = new BasicHttpParams();
		// 设置连接超时时间(单位毫秒)
		HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
		// 设置读数据超时时间(单位毫秒)
		HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);

		HttpClient httpclient = new DefaultHttpClient(httpParams);

		// Prepare a request object
		HttpGet httpget = new HttpGet(url);

		// Execute the request
		HttpResponse response = httpclient.execute(httpget);

		// Examine the response status
		// System.out.println(response.getStatusLine());
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			throw new Exception("Execute a request failed: response status code " + statusCode);
		}

		// Get hold of the response entity
		HttpEntity entity = response.getEntity();

		// If the response does not enclose an entity, there is no need
		// to worry about connection release
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {

				BufferedReader reader = new BufferedReader(new InputStreamReader(instream));

				String line = null;
				while ((line = reader.readLine()) != null) {

					result.append(line);
				}

			} catch (IOException ex) {

				// In case of an IOException the connection will be released
				// back to the connection manager automatically
				throw ex;

			} catch (RuntimeException ex) {

				// In case of an unexpected exception you may want to abort
				// the HTTP request in order to shut down the underlying
				// connection and release it back to the connection manager.
				httpget.abort();
				throw ex;

			} finally {

				// Closing the input stream will trigger connection release
				instream.close();

			}

			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}

		return result.toString();
	}

	public static String postMethodRequest(String url, Map<String, String> params) throws Exception {

		StringBuilder result = new StringBuilder();

		HttpParams httpParams = new BasicHttpParams();
		// 设置连接超时时间(单位毫秒)
		HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
		// 设置读数据超时时间(单位毫秒)
		HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);

		HttpClient httpclient = new DefaultHttpClient(httpParams);
		//add by simon
		httpclient = WebClientDevWrapper.wrapClient(httpclient);
		
		// Prepare a request object
		HttpPost httppost = new HttpPost(url);

		if (params != null && !params.isEmpty()) {
			Set<Map.Entry<String, String>> paramsSet = params.entrySet();
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			for (Map.Entry<String, String> entry : paramsSet) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}

			httppost.setEntity(new UrlEncodedFormEntity(nvps, DEFAULT_CHARSET));
		}

		// Execute the request
		HttpResponse response = httpclient.execute(httppost);

		// Examine the response status
		// System.out.println(response.getStatusLine());
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			throw new Exception("Execute a request failed: response status code " + statusCode);
		}

		// Get hold of the response entity
		HttpEntity entity = response.getEntity();

		// If the response does not enclose an entity, there is no need
		// to worry about connection release
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {

				BufferedReader reader = new BufferedReader(new InputStreamReader(instream));

				String line = null;
				while ((line = reader.readLine()) != null) {

					result.append(line);
				}

			} catch (IOException ex) {

				// In case of an IOException the connection will be released
				// back to the connection manager automatically
				throw ex;

			} catch (RuntimeException ex) {

				// In case of an unexpected exception you may want to abort
				// the HTTP request in order to shut down the underlying
				// connection and release it back to the connection manager.
				httppost.abort();
				throw ex;

			} finally {

				// Closing the input stream will trigger connection release
				instream.close();

			}

			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}

		return result.toString();
	}

	
	public static String postMethodRequest(String url, Map<String, String> params,int connectionTimeout,int soTimeout) throws Exception {

		StringBuilder result = new StringBuilder();

		HttpParams httpParams = new BasicHttpParams();
		// 设置连接超时时间(单位毫秒)
		HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
		// 设置读数据超时时间(单位毫秒)
		HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);

		HttpClient httpclient = new DefaultHttpClient(httpParams);
		// Prepare a request object
		HttpPost httppost = new HttpPost(url);

		if (params != null && !params.isEmpty()) {
			Set<Map.Entry<String, String>> paramsSet = params.entrySet();
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			for (Map.Entry<String, String> entry : paramsSet) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}

			httppost.setEntity(new UrlEncodedFormEntity(nvps, DEFAULT_CHARSET));
		}

		// Execute the request
		HttpResponse response = httpclient.execute(httppost);

		// Examine the response status
		// System.out.println(response.getStatusLine());
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			throw new Exception("Execute a request failed: response status code " + statusCode);
		}

		// Get hold of the response entity
		HttpEntity entity = response.getEntity();

		// If the response does not enclose an entity, there is no need
		// to worry about connection release
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {

				BufferedReader reader = new BufferedReader(new InputStreamReader(instream));

				String line = null;
				while ((line = reader.readLine()) != null) {

					result.append(line);
				}

			} catch (IOException ex) {

				// In case of an IOException the connection will be released
				// back to the connection manager automatically
				throw ex;

			} catch (RuntimeException ex) {

				// In case of an unexpected exception you may want to abort
				// the HTTP request in order to shut down the underlying
				// connection and release it back to the connection manager.
				httppost.abort();
				throw ex;

			} finally {

				// Closing the input stream will trigger connection release
				instream.close();

			}

			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}

		return result.toString();
	}

	public static String postMethodRequestByJson(String url, String jsonString) throws Exception {
		StringBuilder result = new StringBuilder();

		HttpParams httpParams = new BasicHttpParams();
		// 设置连接超时时间(单位毫秒)
		HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
		// 设置读数据超时时间(单位毫秒)
		HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
		
		
		HttpClient httpclient = new DefaultHttpClient(httpParams);
		
		// Prepare a request object
		HttpPost httppost = new HttpPost(url);
		
		//add by simon start
		httpclient = WebClientDevWrapper.wrapClient(httpclient);
		List<Header> headers = new ArrayList<Header>();  
		headers.add(new BasicHeader("Content-Type", APPLICATION_JSON));
		headers.add(new BasicHeader("Accept", APPLICATION_JSON));
		httpclient.getParams().setParameter("http.default-headers", headers);
		StringEntity stringEntity = new StringEntity(jsonString,DEFAULT_CHARSET);
		httppost.setEntity(stringEntity);
		//add by simon end
		
		/*if (params != null && !params.isEmpty()) {
			Set<Map.Entry<String, String>> paramsSet = params.entrySet();
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			for (Map.Entry<String, String> entry : paramsSet) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}

			httppost.setEntity(new UrlEncodedFormEntity(nvps, DEFAULT_CHARSET));
		}*/

		// Execute the request
		HttpResponse response = httpclient.execute(httppost);
		

		// Examine the response status
		// System.out.println(response.getStatusLine());
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			System.out.println(response);
			throw new Exception("Execute a request failed: response status code " + statusCode);
		}

		// Get hold of the response entity
		HttpEntity entity = response.getEntity();

		// If the response does not enclose an entity, there is no need
		// to worry about connection release
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {

				BufferedReader reader = new BufferedReader(new InputStreamReader(instream));

				String line = null;
				while ((line = reader.readLine()) != null) {

					result.append(line);
				}

			} catch (IOException ex) {

				// In case of an IOException the connection will be released
				// back to the connection manager automatically
				throw ex;

			} catch (RuntimeException ex) {

				// In case of an unexpected exception you may want to abort
				// the HTTP request in order to shut down the underlying
				// connection and release it back to the connection manager.
				httppost.abort();
				throw ex;

			} finally {

				// Closing the input stream will trigger connection release
				instream.close();

			}

			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}

		//return result.toString();
		//System.out.println("------>"+result.toString());
		return URLDecoder.decode(result.toString(),DEFAULT_CHARSET);
	}

	public static String postMethodRequestByJson2(String url, String jsonString) throws Exception {
		StringBuilder result = new StringBuilder();

		HttpParams httpParams = new BasicHttpParams();
		// 设置连接超时时间(单位毫秒)
		HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
		// 设置读数据超时时间(单位毫秒)
		HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
		
		
		HttpClient httpclient = new DefaultHttpClient(httpParams);
		
		// Prepare a request object
		HttpPost httppost = new HttpPost(url);
		
		//add by simon start
		httpclient = WebClientDevWrapper.wrapClient(httpclient);
		List<Header> headers = new ArrayList<Header>();  
		headers.add(new BasicHeader("Content-Type", APPLICATION_JSON));
		headers.add(new BasicHeader("Accept", APPLICATION_JSON));
		httpclient.getParams().setParameter("http.default-headers", headers);
		StringEntity stringEntity = new StringEntity(jsonString,DEFAULT_CHARSET);
		httppost.setEntity(stringEntity);
		//add by simon end
		
		/*if (params != null && !params.isEmpty()) {
			Set<Map.Entry<String, String>> paramsSet = params.entrySet();
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			for (Map.Entry<String, String> entry : paramsSet) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}

			httppost.setEntity(new UrlEncodedFormEntity(nvps, DEFAULT_CHARSET));
		}*/

		// Execute the request
		HttpResponse response = httpclient.execute(httppost);
		

		// Examine the response status
		// System.out.println(response.getStatusLine());
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			System.out.println(response);
			throw new Exception("Execute a request failed: response status code " + statusCode);
		}

		// Get hold of the response entity
		HttpEntity entity = response.getEntity();

		// If the response does not enclose an entity, there is no need
		// to worry about connection release
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {

				BufferedReader reader = new BufferedReader(new InputStreamReader(instream));

				String line = null;
				while ((line = reader.readLine()) != null) {

					result.append(line);
				}

			} catch (IOException ex) {

				// In case of an IOException the connection will be released
				// back to the connection manager automatically
				throw ex;

			} catch (RuntimeException ex) {

				// In case of an unexpected exception you may want to abort
				// the HTTP request in order to shut down the underlying
				// connection and release it back to the connection manager.
				httppost.abort();
				throw ex;

			} finally {

				// Closing the input stream will trigger connection release
				instream.close();

			}

			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}

		//return result.toString();
		//System.out.println("------>"+result.toString());
		//return URLDecoder.decode(result.toString(),DEFAULT_CHARSET);
		return result.toString();
	}
	
	public static void main(String[] args) throws Exception {

		System.out.println("result:" + HttpClientUtils.getMethodRequest("http://dev.hongkong.api2.ibutterfly.net:5080/iButterfly_WebService/getButterflyDistributionQuota.do"));

		// System.out.println("result:" + HttpClientUtils.getMethodRequest("http://www.cherrypicks.com"));

		Map<String, String> params = new HashMap<String, String>();
		params.put("clientOS", "wp7");
		params.put("lang", "zh");
		System.out.println("result:" + HttpClientUtils.postMethodRequest("http://office2.cherrypicks.com:18088/TownCheck_WebService/webservice/channel/getAllChannel.do", params));
		
		
	}
}
