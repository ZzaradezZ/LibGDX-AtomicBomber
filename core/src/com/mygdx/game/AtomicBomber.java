package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import jdk.vm.ci.code.Register;
import model.GameInfo;
import model.User;
import view.GameScreen;
import view.RegisterMenu;

import java.io.IOException;

public class AtomicBomber extends Game {
	public SpriteBatch batch;
	public Skin skin;
	public static final int WIDTH = 2560;
	public static final int HEIGHT = 1440;
	public GameInfo gameInfo;
	public BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
        this.setScreen(new RegisterMenu(this));
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		Gdx.input.setInputProcessor(null);
	}
}
