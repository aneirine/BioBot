package com.example.biobot.anonymous;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

@Getter
@Setter
@EqualsAndHashCode
public final class Anonymous {

    private static final Logger LOG = LogManager.getLogger(Anonymous.class);

    private final User user;
    private final Chat chat;
    private String displayedName;

    public Anonymous(User user, Chat chat) {
        if (user == null || chat == null) {
            LOG.error("USER_CHAT_CANNOT_BE_NULL");
            throw new IllegalStateException("USER_CHAT_CANNOT_BE_NULL");
        }
        this.user = user;
        this.chat = chat;
    }


}
