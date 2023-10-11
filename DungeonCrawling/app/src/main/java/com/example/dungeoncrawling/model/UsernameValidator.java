package com.example.dungeoncrawling.model;

public class UsernameValidator {
    public static boolean isValidUsername(String username) {
        if (username != null) {
            for (int i = 0; i < username.length(); i++) {
                char c = username.charAt(i);
                if (!Character.isWhitespace(c)) {
                    return true;
                }
            }
        }
        return false;
    }
}
