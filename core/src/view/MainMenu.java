package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.AtomicBomber;
import model.User;

public class MainMenu implements Screen {
    AtomicBomber game;
    private Sprite sprite;
    private Stage stage;
    private Table table;
    private Skin skin;

    private TextButton settingButton;
    private TextButton profileMenuButton;
    private TextButton playGameButton;
    private TextButton exitButton;
    private TextButton showLeaderBoardButton;



    public MainMenu(AtomicBomber game) {
        this.game = game;
        sprite = new Sprite();
        Texture backgroundTexture = new Texture("menu/background1.png");
        this.sprite = new Sprite(backgroundTexture);
        this.sprite.setSize(AtomicBomber.WIDTH, AtomicBomber.HEIGHT);
        skin = game.skin;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        showLeaderBoardButton = new TextButton("LeaderBoard", skin);
        showLeaderBoardButton.setSize(400, 200);
        showLeaderBoardButton.setPosition(1700, 400);

        settingButton = new TextButton("Setting", skin);
        settingButton.setSize(400, 100);
        settingButton.setPosition(1700, 700);

        profileMenuButton = new TextButton("Profile Menu", skin);
        profileMenuButton.setSize(400, 100);
        profileMenuButton.setPosition(1700, 600);

        playGameButton = new TextButton("Play Game", skin);
        playGameButton.setSize(600, 340);
        playGameButton.setPosition(1600, 800);

        exitButton = new TextButton("Exit", skin);
        exitButton.setColor(Color.RED);
        exitButton.setSize(200, 60);
        exitButton.setPosition(1800, 300);


        table.addActor(settingButton);
        table.addActor(profileMenuButton);
        table.addActor(playGameButton);
        table.addActor(exitButton);
        table.addActor(showLeaderBoardButton);

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                User.saveUsers();
                Gdx.app.exit();
            }
        });
        profileMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new ProfileMenu(game));
            }
        });
        playGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new GameScreen(game));
            }
        });
        settingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new Setting(game));
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0.25f, 0.5f, 0.75f, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        sprite.draw(game.batch);
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
