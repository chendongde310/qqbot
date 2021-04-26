package com.my.qqbot;

import com.alibaba.fastjson.JSONObject;
import com.my.qqbot.service.HttpHandler;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class QqbotApplicationTests {

	@Test
	void contextLoads() {



		Request request = new Request.Builder()
				.url("https://api.vvhan.com/api/mobil.girl")
				.build();
		try (Response response = HttpHandler.Handler().client.newCall(request).execute()) {
			String html = response.body().string();
			int a = html.indexOf("https");
			int b = html.indexOf("png")+3;
			System.out.println(html.substring(a,b));
		} catch (IOException e) {
			e.printStackTrace();
		}


	}



}
