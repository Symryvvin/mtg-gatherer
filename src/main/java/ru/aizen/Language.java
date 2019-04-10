package ru.aizen;

import java.util.Arrays;

public enum Language {
    CN("Chinese Simplified", "cs"),
    ZH_TW("Chinese Traditional", "ct"),
    DE("German", "de"),
    EN("English", "en"),
    FR("French", "fr"),
    IT("Italian", "it"),
    JA("Japanese", "ja"),
    KO("Korean", "ko"),
    PT_BR("Portuguese (Brazil)", "pt"),
    RU("Russian", "ru"),
    ES("Spanish", "es");

    private String name;
    private String code;

    Language(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static Language from(String language) {
        return Arrays.stream(Language.values())
                .filter(l -> l.name.equals(language))
                .findFirst()
                .orElse(null);
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}