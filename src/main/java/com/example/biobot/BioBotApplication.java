package com.example.biobot;


import com.example.biobot.bot.BioBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;


public class BioBotApplication {

    private static final String PROXY_HOST = "xx.xx.xxx.xxx";
    private static final int PROXY_PORT = 9999;

    public static void main(String[] args) {

        try {

            ApiContextInitializer.init();
            TelegramBotsApi botsApi = new TelegramBotsApi();


            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

            botOptions.setProxyHost(PROXY_HOST);
            botOptions.setProxyPort(PROXY_PORT);
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS4);

            botsApi.registerBot(new BioBot());

        } catch (Exception e) {

        }

    }


}
