package com.example.biobot.server;

import com.example.biobot.Language;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerConnection {

    public static final String URL = "http://13.94.229.153:8080/api/v1/ask";
    public static final String ENGLISH = "en";
    public static final String UKRAINIAN = "uk";


    public void sendGetRequest(String message, Language language) throws Exception {

        HttpURLConnection connection = null;

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
            System.out.printf("RESPONSE " + response.toString());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
/
}
