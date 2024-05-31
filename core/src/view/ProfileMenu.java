package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.AtomicBomber;
import model.User;

public class ProfileMenu implements Screen {
    AtomicBomber game;
    private Sprite sprite;
    private Stage stage;
    private Skin skin;
    private Texture background;
    private Dialog dialog;


    private TextField usernameField;
    private TextField passwordField;
    private TextButton changeUsername;
    private TextButton changePassword;

    private TextButton removeAccount;
    private TextButton logout;
    private TextButton returnToMainMenu;
    Texture backgroundTexture;


    public ProfileMenu(AtomicBomber game) {
        this.game = game;
        backgroundTexture = new Texture("menu/background.png");
        sprite = new Sprite(backgroundTexture);
        sprite.setSize(AtomicBomber.WIDTH, AtomicBomber.HEIGHT);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = game.skin;

        usernameField = new TextField("", skin);
        usernameField.setSize(400, 100);
        usernameField.setPosition(200, 500);
        usernameField.setMessageText("Enter your username");
        passwordField = new TextField("", skin);
        passwordField.setMessageText("Enter your password");
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');
        passwordField.setSize(400, 100);
        passwordField.setPosition(200, 350);
        changeUsername = new TextButton("Change\nUsername", skin);
        changeUsername.setSize(300, 100);
        changeUsername.setPosition(600, 500);
        changePassword = new TextButton("Change\nPassword", skin);
        changePassword.setSize(300, 100);
        changePassword.setPosition(600, 350);
        removeAccount = new TextButton("Remove Account", skin);
        removeAccount.setSize(400, 100);
        removeAccount.setPosition(100, 150);
        logout = new TextButton("Logout", skin);
        logout.setSize(400, 100);
        logout.setPosition(100, 50);
        returnToMainMenu = new TextButton("Return", skin);
        returnToMainMenu.setPosition(50, AtomicBomber.HEIGHT - 150);
        returnToMainMenu.setSize(300, 100);

        stage.addActor(removeAccount);
        stage.addActor(usernameField);
        stage.addActor(passwordField);
        stage.addActor(changeUsername);
        stage.addActor(changePassword);
        stage.addActor(logout);
        stage.addActor(returnToMainMenu);

    }

    @Override
    public void show() {
        removeAccount.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                User.removeUser(User.getLoggedInUser());
            }
        });


        logout.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                User.setLoggedInUser(null);
                dispose();
                game.setScreen(new RegisterMenu(game));
            }
        });

        changeUsername.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String username = usernameField.getText();
                User.getLoggedInUser().changeUsername(username, skin, stage);
            }
        });

        changePassword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String password = passwordField.getText();
                User.getLoggedInUser().changePassword(password, stage, skin);
            }
        });

        returnToMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MainMenu(game));
            }
        });
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();
        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
