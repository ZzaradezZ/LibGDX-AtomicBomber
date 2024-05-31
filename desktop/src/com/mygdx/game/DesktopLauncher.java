package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import model.User;
import java.io.IOException;
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("My GDX Game");
		config.setWindowedMode(AtomicBomber.WIDTH, AtomicBomber.HEIGHT);
		config.setResizable(false);
        try {
            User.readUsers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new Lwjgl3Application(new AtomicBomber(), config);
	}
}