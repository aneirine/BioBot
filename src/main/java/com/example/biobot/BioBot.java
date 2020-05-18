package com.example.biobot;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

public final class BioBot extends TelegramLongPollingBot {

    private static final Logger LOG = LogManager.getLogger(Anonymous.class);

    private final User user;
    private final Chat chat;

    public BioBot(User user, Chat chat) {
        if (user == null || chat == null) {
            LOG.error("USER_CHAT_CANNOT_BE_NULL");
            throw new IllegalStateException("USER_CHAT_CANNOT_BE_NULL");
        }
        this.user = user;
        this.chat = chat;
    }

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