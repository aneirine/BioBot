package com.example.biobot.server;

import com.example.biobot.Language;
import com.example.biobot.server.util.ParameterStringBuilder;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ServerConnection {

    public static final String URL = "http://13.94.229.153:8080/api/v1/ask";
    public static final String ENGLISH = "en";
    public static final String UKRAINIAN = "uk";


    public void sendGet(String message, Language language) throws Exception {

        URL url = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("question", message);
        parameters.put("lang", language == Language.ENGLISH ? ENGLISH : UKRAINIAN);

        connection.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();


        connection.setRequestMethod("GET");


    }


}
