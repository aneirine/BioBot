package com.example.biobot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public final class BioBot extends TelegramLongPollingBot {

    private static final String BOT_NAME = "BioBot";
    private static final String BOT_TOKEN = "887712066:AAH9iCyuutngQo62jxqw0Sf_ObqUNkK7Vlc";
    private static Language language;


    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String text = message.getText();
        if (text.equals("/start")) {
            sendMessage(message, "Hello. Please, choose the language: /english /ukrainian");
        } else if (text.equals("/english")) {
            language = Language.ENGLISH;
            sendMessage(message, "Chosen language is English");
        } else if (text.equals("/ukrainian")) {
            language = Language.UKRAINIAN;
            sendMessage(message, "Обрана українська мова");
        } else {
            sendMessage(message, "I don't understand you");
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    private void sendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(text);
        try {
            sendMessageToUser(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessageToUser(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}