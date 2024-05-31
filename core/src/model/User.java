package model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class User implements Serializable {
    public static ArrayList<User> allUsers = new ArrayList<>();
    private String username;
    private String password;
    private static User loggedInUser;
    Dialog dialog;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        allUsers.add(this);
        saveUsers();
    }

    public static void saveUsers() {
        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get("allUsers.dat")))){
            output.writeObject(allUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readUsers() throws IOException {
        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get("allUsers.dat")))) {
                ArrayList<User> users = (ArrayList<User>) input.readObject();
                allUsers.addAll(users);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static User getUserByName(String username) {
        for (User entry : allUsers) {
            if (entry.username.equals(username))
                return entry;
        }
        return null;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User logged) {
        loggedInUser = logged;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }


    public static void removeUser(User user) {
        allUsers.remove(user);
        saveUsers();
    }

    public void changeUsername(String username, Skin skin, Stage stage) {
        if (username.equals(this.username)) {
            showMassage("username is already this", stage, skin);
        } else {
            showMassage("username changed", stage, skin);
        }
        this.username = username;
        saveUsers();
    }

    public void changePassword(String password, Stage stage, Skin skin) {
        this.password = password;
        showMassage("password changed", stage, skin);
    }



    public void showMassage(String massage, Stage stage, Skin skin) {
        dialog = new Dialog("", skin);
        dialog.setColor(Color.BLUE);
        dialog.text(massage);
        dialog.show(stage);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                dialog.hide();
            }
        }, 2f);

    }
}