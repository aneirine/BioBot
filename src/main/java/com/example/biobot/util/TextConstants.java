package com.example.biobot.util;

import com.vdurmont.emoji.EmojiParser;

public class TextConstants {

    public static final String heartEmoji = ":heart:";
    public static final String uaEmoji = ":ua:";
    public static final String gbEmoji = ":gb:";
    public static final String maskEmoji = ":mask:";
    public static final String helpEmoji = ":thought_balloon:";
    public static final String thinkEmoji = ":think:";
    public static final String languageEmoji = ":speaking_head_in_silhouette:";
    public static final String exclamationEmoji = ":exclamation:";
    public static final String sparklesEmoji = ":sparkles:";
    public static final String doneEmoji = ":white_check_mark:";
    public static final String questionEmoji = ":question:";

    public static final String ENGLISH_START = EmojiParser.parseToUnicode(heartEmoji + " Welcome to BioBot." +
            "\n" + maskEmoji + " This bot can help you to solve different problem with health and find specific information according to your request." +
            "\n" + exclamationEmoji + " Please, before start choose the language. " +
            "\n" + gbEmoji + " If you want english try /english . " +
            "\n" + helpEmoji + " If you don't understand something, try /help ." +
            "\n" + languageEmoji + " If you want to change language, try /language" +
            "\n" + questionEmoji + " Ask bot about your problem");

    public static final String UKRAINIAN_START = EmojiParser.parseToUnicode(heartEmoji + " Вітаємо у BioBot." +
            "\n" + maskEmoji + " Цей бот створений щоб допомогти Вам з вирішенням різних проблем, пов'язаних зі станом здоров'я, або знайти конкретну інформацію медичного напрямку за вашим запитом. " +
            "\n" + exclamationEmoji + " Будь ласка, перед початком взаємодії оберіть мову." +
            "\n" + uaEmoji + " Якщо бажана мова для вас - українська, використайте /ukrainian . " +
            "\n" + helpEmoji + " Якщо ви чогось не розумієте, спробуйте /help ." +
            "\n" + languageEmoji + " Якщо ви бажаєте змінити мову, використайте /language" +
            "\n" + questionEmoji + " Запитайте у бота про свою проблему");

    public static final String LANGUAGE_COMMAND = EmojiParser.parseToUnicode(gbEmoji + " If you want English, try /english.\n" + uaEmoji + " Якщо бажана мова для вас - українська, використайте /ukrainian");
    public static final String LANGUAGE_SELECTION = EmojiParser.parseToUnicode(exclamationEmoji + " Please, choose the language /english.\n" + exclamationEmoji + " Будь ласка, оберіть мову /ukrainian.");
    public static final String ENGLISH_CHOSEN = EmojiParser.parseToUnicode(gbEmoji + " Chosen language is English");
    public static final String UKRAINIAN_CHOSEN = EmojiParser.parseToUnicode(uaEmoji + " Обрана українська мова");


    public static final String ENGLISH_HELP = EmojiParser.parseToUnicode(sparklesEmoji + " All commands: " +
            "\n" + doneEmoji + " /start - start the bot " +
            "\n" + thinkEmoji + " /help - see all commands " +
            "\n" + languageEmoji + " /language - choose the language");
    public static final String UKRAINIAN_HELP = EmojiParser.parseToUnicode(sparklesEmoji + " Усі команди: " +
            "\n" + doneEmoji + " /start - розпочати роботу бота  " +
            "\n" + thinkEmoji + " /help - побачити усі команди " +
            "\n" + languageEmoji + " /language - змінити мову ");


}
