package view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.AtomicBomber;
import controller.LoginMenuApp;
import controller.RegisterMenuApp;
import model.GameInfo;
import model.Resault;
import model.User;

public class RegisterMenu extends ApplicationAdapter implements Screen {
    AtomicBomber game;
    private Sprite sprite;
    private Stage stage;
    private static final Table table = new Table();
    private Skin skin;
    private Dialog errorDialog;
    private Label passwordStrengthLabel;

    private TextButton loginButton;
    private TextButton registerButton;
    private TextButton guestButton;
    TextField usernameField;
    TextField passwordField;

    public RegisterMenu(AtomicBomber game) {
        this.game = game;
        sprite = new Sprite();
        Texture background = new Texture("menu/background.png");
        stage = new Stage();
        sprite = new Sprite(background);
        sprite.setSize(AtomicBomber.WIDTH, AtomicBomber.HEIGHT);
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        table.setFillParent(true);

        registerButton = new TextButton("Register", skin);
        loginButton = new TextButton("Login", skin);
        guestButton = new TextButton("Guest", skin);
        usernameField = new TextField("", skin);
        usernameField.setMessageText("Enter your username");
        passwordField = new TextField("", skin);
        passwordField.setMessageText("Enter your password");
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');


        float registerButtonX = (float) Gdx.graphics.getWidth() / 2 - registerButton.getWidth() / 2;
        float registerButtonY = (float) Gdx.graphics.getHeight() / 2 - registerButton.getHeight() / 2 - 200;
        float loginButtonX = (float) Gdx.graphics.getWidth() / 2 - loginButton.getWidth() / 2 + 270;
        float loginButtonY = (float) Gdx.graphics.getHeight() / 2 - loginButton.getHeight() / 2 - 200;
        float guestButtonX = (float) Gdx.graphics.getWidth() / 2 - guestButton.getWidth() / 2 + 100;
        float guestButtonY = (float) Gdx.graphics.getHeight() / 2 - guestButton.getHeight() / 2 - 290;

        registerButton.setSize(500, 160);
        loginButton.setSize(500, 160);
        guestButton.setSize(500, 160);
        usernameField.setSize(500, 100);
        passwordField.setSize(500, 100);
        usernameField.setPosition(1900, 1000);
        passwordField.setPosition(1900, 900);
        registerButton.setPosition(1900, 600);
        loginButton.setPosition(1900, 400);
        guestButton.setPosition(1900, 200);

        table.add(passwordField);
        table.add(usernameField);
        stage.addActor(table);
        stage.addActor(usernameField);
        stage.addActor(passwordField);
        stage.addActor(registerButton);
        stage.addActor(loginButton);
        stage.addActor(guestButton);
    }

    public void showMassage(Resault resault) {
        String title;
        if (resault.valid) {
            title = "successful";
        } else {
            title = "error";
        }
        Dialog dialog = new Dialog(title, skin);
        if (resault.valid) {
            dialog.setColor(Color.GREEN);
        } else {
            dialog.setColor(Color.RED);
        }
        dialog.button("OK");
        dialog.text(resault.massage);
        dialog.show(stage);
    }

    @Override
    public void create() {


    }

    @Override
    public void show() {
        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                Resault usernameResault = RegisterMenuApp.validUsername(username);
                Resault passwordResault = RegisterMenuApp.validPassword(password, username);
                if (!usernameResault.valid) {
                    showMassage(usernameResault);
                    return;
                }
                if (!passwordResault.valid) {
                    showMassage(passwordResault);
                    return;
                }
                showMassage(usernameResault);
                RegisterMenuApp.register(username, password);
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                Resault resault = LoginMenuApp.login(username, password);
                showMassage(resault);
                usernameField.setText("");
                passwordField.setText("");
                if (resault.valid) {
                    game.gameInfo = new GameInfo(false);
                    dispose();
                    game.setScreen(new MainMenu(game));
                }
            }
        });

        guestButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.gameInfo = new GameInfo(true);
                dispose();
                game.setScreen(new GameScreen(game));
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        sprite.draw(game.batch);
        game.batch.end();
        stage.act(delta);
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
        table.clear();
    }
}
