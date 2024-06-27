package controller;

import jdk.internal.foreign.ArenaAllocator;
import model.Resault;
import model.User;
import model.enums.Regex;

import java.util.ArrayList;

public class RegisterMenuApp {
    private static String massage;
    private static boolean valid;



    public static Resault validUsername(String username) {
        for (User user : User.getAllUsers()) {
            if (user.getUsername().equals(username)) {
                return new Resault("username has already exists", false);
            }
        }
        if (Regex.USERNAME.getMatcher(username).matches()) {
            valid = true;
            massage = "registered";
        } else {
            valid = false;
            massage = "username is invalid";
        }
        return new Resault(massage, valid);
    }

    public static Resault validPassword(String password, String username) {
        if (!Regex.PASSWORD.getMatcher(password).matches()) {
            return new Resault("Password is weak", false);
        }
        return new Resault("registered", true);
    }

    public static void register(String username, String password) {
        User user = new User(username, password);
        User.setLoggedInUser(user);
    }

}
