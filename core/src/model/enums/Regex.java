package model.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Regex {
    USERNAME("[a-zA-Z0-9_]*"),
    PASSWORD("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]+"),
    LOWERCASE(".*[a-z].*"),
    UPPERCASE(".*[A-Z].*"),
    NUMBER(".*[0-9].*"),
    SPECIAL_CHAR(".*[!@#$%^&].*"),
    ;
    private String regex;

    Regex(String regex) {
        this.regex = regex;
    }


    public Matcher getMatcher(String input) {
        return Pattern.compile(regex).matcher(input);
    }
}
