package com.example.biobot;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Objects;

public final class BioBot extends TelegramLongPollingBot {

    private static final String BOT_NAME = "BioBot";
    private static final String BOT_TOKEN = "887712066:AAH9iCyuutngQo62jxqw0Sf_ObqUNkK7Vlc";

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return null;
    }




}