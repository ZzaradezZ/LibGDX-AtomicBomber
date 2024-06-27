package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.AtomicBomber;
import model.User;

public class Setting implements Screen {
    AtomicBomber game;
    private Sprite sprite;
    private Stage stage;
    private Skin skin;


    private TextButton muteButton;
    private TextButton backButton;
    private CheckBox mute;

    public Setting(AtomicBomber game) {
        this.game = game;
        Texture backgroundTexture = new Texture("menu/background.png");
        sprite = new Sprite(backgroundTexture);
        sprite.setSize(AtomicBomber.WIDTH, AtomicBomber.HEIGHT);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = game.skin;

        muteButton = new TextButton("Mute", skin);
        muteButton.setSize(200, 60);
        muteButton.setPosition(1200, 1000);

        backButton = new TextButton("Back", skin);
        backButton.setSize(200, 60);
        backButton.setPosition(1200, 200);

        stage.addActor(muteButton);
        stage.addActor(backButton);

    }

    @Override
    public void show() {
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MainMenu(game));
            }
        });
        muteButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameScreen.mute = !GameScreen.mute;
            }
        });

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

    }
}
