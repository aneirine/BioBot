package com.example.biobot.anonymous;

import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class AnonymousService {

    private final Set<Anonymous> anonymouses;

    public AnonymousService() {
        anonymouses = new HashSet<>();
    }

    public boolean setUserDisplayedName(User user, String name) {
        if (!isDisplayedNameTaken(name)) {
            anonymouses.stream().filter(a -> a.getUser().equals(user)).forEach(a -> a.setDisplayedName(name));
            return true;
        }
        return false;
    }

    public boolean removeAnonymous(User user) {
        return anonymouses.removeIf(a -> a.getUser().equals(user));
    }

    public boolean addAnonymous(Anonymous anonymous) {
        return anonymouses.add(anonymous);
    }

    public boolean hasAnonymous(User user) {
        return anonymouses.stream().anyMatch(a -> a.getUser().equals(user));
    }

    public String getDisplayedName(User user) {

        Anonymous anonymous = anonymouses.stream().filter(a -> a.getUser().equals(user)).findFirst().orElse(null);

        if (anonymous == null) {
            return null;
        }
        return anonymous.getDisplayedName();
    }

    public Stream<Anonymous> anonymouses() {
        return anonymouses.stream();
    }

    private boolean isDisplayedNameTaken(String name) {
        return anonymouses.stream().anyMatch(a -> Objects.equals(a.getDisplayedName(), name));
    }
}
