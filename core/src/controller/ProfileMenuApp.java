package controller;

import model.Resault;

public class ProfileMenuApp {
    private static String massage;
    private static boolean valid;
    public static Resault deleteAccount() {



        return new Resault(massage, valid);
    }

    public static void logout() {

    }

    public static Resault changeUsername() {
        return new Resault(massage, valid);
    }

    public static Resault changePassword() {
        return new Resault(massage, valid);
    }



}
