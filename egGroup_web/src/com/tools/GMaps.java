package com.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GMaps {
	// 關鍵字搜尋

	public static HashMap<String, Double> Gmap(String address) throws Exception {

//		System.out.println("進入map");
		HashMap<String, Double> latLot = new HashMap<String, Double>();

		Gson gson = new Gson();
		// 讀回json字串
		String adrs = address.trim();
		String SearchAdrs = "https://maps.googleapis.com/maps/api/geocode/json?" + 
							"address=" + java.net.URLEncoder.encode(adrs, "UTF-8") + 
							"&language=zh-TW" +
							"&key=AIzaSyBInFfSY1JNb6TnRXeODk1XpgWK84J_kjs";

//		System.out.println(address);

		InputStream is = new URL(SearchAdrs).openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		StringBuilder sb = new StringBuilder();
		String str;

		while ((str = br.readLine()) != null)
			sb.append(str);
		br.close();
		
		// 解析json內容
		if (sb.length() > 0) {
			JsonObject jObj = gson.fromJson(sb.toString(), JsonObject.class);
			JsonArray jArray = jObj.get("results").getAsJsonArray();
			for (int i = 0; i < jArray.size(); i++) {
				JsonObject obj = jArray.get(i).getAsJsonObject();
				if (obj.has("rating"))

					obj.get("rating").getAsFloat();
				double lot = obj.get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lng")
						.getAsDouble();
				double lat = obj.get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lat")
						.getAsDouble();

//				System.out.println(lot);
//				System.out.println(lat);

				latLot.put("lot", lot);
				latLot.put("lat", lat);

			}
		}
		return latLot;
	}

}