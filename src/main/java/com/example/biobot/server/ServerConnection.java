package com.example.biobot.server;

import com.example.biobot.enums.Language;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ServerConnection {

    public static final String URL = "http://13.94.229.153:8080/api/v1/ask";
    public static final String ENGLISH = "en";
    public static final String UKRAINIAN = "uk";


    public String sendGetRequest(String message, Language language) {
        try {

            URIBuilder builder = new URIBuilder(URL);
            builder.setParameter("question", message)
                    .setParameter("lang", language == Language.ENGLISH ? ENGLISH : UKRAINIAN);

            HttpRequestBase request = new HttpGet(builder.build());
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse resp = httpClient.execute(request);

            InputStream is = resp.getEntity().getContent();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            JSONObject object = new JSONObject(response);
            return  object.getString("translated_answer");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
