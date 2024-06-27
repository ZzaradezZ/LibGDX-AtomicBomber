package controller;

import model.Resault;
import model.User;

public class LoginMenuApp {
    private static String massage;
    private static boolean valid;


    public static Resault login(String username, String password) {
        User user = User.getUserByName(username);
        if (user == null) {
            return new Resault("username does not exists", false);
        }
        if (!user.getPassword().equals(password)) {
            return new Resault("password is wrong", false);
        }
        User.setLoggedInUser(user);
        return new Resault("logged in", true);
    }

}
