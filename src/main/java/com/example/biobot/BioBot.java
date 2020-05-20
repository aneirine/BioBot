package com.example.biobot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public final class BioBot extends TelegramLongPollingBot {

    private static final String BOT_NAME = "BioBot";
    private static final String BOT_TOKEN = "887712066:AAH9iCyuutngQo62jxqw0Sf_ObqUNkK7Vlc";
    private static Language language = Language.ENGLISH;


    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();

        switch (message) {
            case "/start":
                startCommand(update.getMessage().getChatId());
                break;
            case "/language":
                sendMessageToUser(setButtons(constructMessage(update.getMessage().getChatId().toString(),
                        "Please, choose the language.\nБудь ласка, оберіть мову.")));
                break;
            case "English":
            case "Українська":
                boolean english = message.equals("English");
                language = english ? Language.ENGLISH : Language.UKRAINIAN;
                sendMessageToUser(constructMessage(update.getMessage().getChatId().toString(),
                        english ? "Chosen language is English" : "Обрана українська мова"));
                break;
            case "/help":
                sendMessageToUser(constructMessage(update.getMessage().getChatId().toString(),
                        language == Language.ENGLISH ?
                                "All commands: \n /start - start the bot \n /stop - stop the bot \n /help - see all commands \n /language - choose the language " :
                                "Усі комманди: \n /start - розпочати роботу бота \n /stop - зупинити роботу бота  \n /help - побачити усі команди \n /language - змінити мову "

                ));
                break;
            default:
                sendMessageToUser(constructMessage(update.getMessage().getChatId().toString(),
                        language == Language.ENGLISH ?
                                "I don't understand you. Try /help to see all available commands" :
                                "Я вас не розумію. Спробуйте комманду /help щоб побачити усі можливі комманди"));
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
                "\nThe default language is English, if you want to change it, try /language";
        String ukrainianMessage = "Вітаємо у BioBot. Цей бот створений щоб допомогти Вам з вирішенням різних проблем, пов'язаних зі станом здоров'я, або знайти конкретну інформацію медичного напрямку за вашим запитом. " +
                "\nОбрана мова - українська. Якщо ви бажаєте змінити мову використайте комманду /language";
        SendMessage sendMessage = constructMessage(chatId.toString(),
                language == Language.ENGLISH ? englishMessage : ukrainianMessage);
        //  sendMessage = setButtons(sendMessage);
        sendMessageToUser(sendMessage);
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

    public synchronized SendMessage setButtons(SendMessage sendMessage) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();


        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Українська"));


        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("English"));


        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
        return sendMessage;
    }


}