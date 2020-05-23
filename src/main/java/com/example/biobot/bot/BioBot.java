package com.example.biobot.bot;

import com.example.biobot.enums.Language;
import com.example.biobot.server.ServerConnection;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.example.biobot.util.TextConstants.*;

public final class BioBot extends TelegramLongPollingBot {

    private static final String BOT_NAME = "BioBot";
    private static final String BOT_TOKEN = "887712066:AAH9iCyuutngQo62jxqw0Sf_ObqUNkK7Vlc";
    private static Language language;

    //text


    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();

        switch (message) {
            case "/start":
                startCommand(update.getMessage().getChatId());
                break;
            case "/language":
                sendMessageToUser(constructMessage(update.getMessage().getChatId().toString(),
                        LANGUAGE_COMMAND));
                break;
            case "/english":
                language = Language.ENGLISH;
                sendMessageToUser(constructMessage(update.getMessage().getChatId().toString(),
                        ENGLISH_CHOSEN));
                break;
            case "/ukrainian":
                language = Language.UKRAINIAN;
                sendMessageToUser(constructMessage(update.getMessage().getChatId().toString(),
                        UKRAINIAN_CHOSEN));
                break;
            case "/help":
                sendMessageToUser(constructMessage(update.getMessage().getChatId().toString(),
                        language == Language.ENGLISH ? ENGLISH_HELP : UKRAINIAN_HELP
                ));
                break;
            default:
                if (language == null) {
                    languageSelection(update.getMessage().getChatId().toString());
                } else {
                    try {
                        String answer = new ServerConnection().sendGetRequest(message, language);
                        sendMessageToUser(constructMessage(update.getMessage().getChatId().toString(), answer));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
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

    private void startCommand(Long chatId) {


        SendMessage sendMessage = constructMessage(chatId.toString(), ENGLISH_START + "\n\n" + UKRAINIAN_START);
        sendMessageToUser(sendMessage);
    }

    private void languageSelection(String chatId) {
        sendMessageToUser(constructMessage(chatId, LANGUAGE_SELECTION));
    }

    public synchronized SendMessage constructMessage(String chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        return sendMessage;
    }


    private void sendMessageToUser(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}