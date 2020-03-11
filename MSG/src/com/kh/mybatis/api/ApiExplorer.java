package com.kh.mybatis.api;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
	public static void main(String[] args) throws IOException {
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=CI5TYki0ax2rijct1Cy6pgRcbc6YBIwu4kR86UGRMXELMi5%2Bcypr0MkMZ%2FRIddKGRdGNjmFEjRhZ5MtsV4Onbw%3D%3D"); /* Service Key */
		urlBuilder
				.append("&" + URLEncoder.encode("solYear", "UTF-8") + "=" + URLEncoder.encode("2020", "UTF-8")); /* 연 */
		urlBuilder
				.append("&" + URLEncoder.encode("solMonth", "UTF-8") + "=" + URLEncoder.encode("09", "UTF-8")); /* 월 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
	}
}