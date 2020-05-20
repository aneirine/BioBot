package com.example.biobot.bot;

import com.example.biobot.enums.Language;
import com.example.biobot.server.ServerConnection;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public final class BioBot extends TelegramLongPollingBot {

    private static final String BOT_NAME = "BioBot";
    private static final String BOT_TOKEN = "887712066:AAH9iCyuutngQo62jxqw0Sf_ObqUNkK7Vlc";
    private static Language language;


    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();

        switch (message) {
            case "/start":
                startCommand(update.getMessage().getChatId());
                break;
            case "/language":
                sendMessageToUser(constructMessage(update.getMessage().getChatId().toString(),
                        "If you want English, try /english.\n Якщо бажана мова для вас - українська, використайте /ukrainian"));
                break;
            case "/english":
                language = Language.ENGLISH;
                sendMessageToUser(constructMessage(update.getMessage().getChatId().toString(), "Chosen language is English"));
                break;
            case "/ukrainian":
                language = Language.UKRAINIAN;
                sendMessageToUser(constructMessage(update.getMessage().getChatId().toString(), "Обрана українська мова"));
                break;
            case "/help":
                sendMessageToUser(constructMessage(update.getMessage().getChatId().toString(),
                        language == Language.ENGLISH ?
                                "All commands: \n /start - start the bot \n /help - see all commands \n /language - choose the language " :
                                "Усі команди: \n /start - розпочати роботу бота  \n /help - побачити усі команди \n /language - змінити мову "

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

        String englishMessage = "Welcome to BioBot. This bot can help you to solve different problem with health and find specific information according to your request." +
                "\nPlease, before start choose the language. If you want english try /english . " +
                "\nIf you don't understand something, try /help ." +
                "\nIf you want to change language, try /language";
        String ukrainianMessage = "Вітаємо у BioBot. Цей бот створений щоб допомогти Вам з вирішенням різних проблем, пов'язаних зі станом здоров'я, або знайти конкретну інформацію медичного напрямку за вашим запитом. " +
                "\nБудь ласка, перед початком взаємодії оберіть мову. Якщо бажана мова для вас - українська, використайте /ukrainian . " +
                "\nЯкщо ви чогось не розумієте, спробуйте /help ." +
                "\nЯкщо ви бажаєте змінити мову, використайте /language";
        SendMessage sendMessage = constructMessage(chatId.toString(), englishMessage + "\n\n" + ukrainianMessage);
        sendMessageToUser(sendMessage);
    }

    private void languageSelection(String chatId) {
        sendMessageToUser(constructMessage(chatId,
                "Please, choose the language /english.\nБудь ласка, оберіть мову /ukrainian."));
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