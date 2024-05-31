package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.AtomicBomber;

public class SettingInGame implements Screen {
    AtomicBomber game;
    private Sprite sprite;
    private Stage stage;
    private Skin skin;

    private TextButton muteButton;
    private TextButton backButton;

    public SettingInGame(AtomicBomber game) {
        this.game = game;
        Texture backgroundTexture = new Texture("menu/background.png");
        sprite = new Sprite(backgroundTexture);
        sprite.setSize(AtomicBomber.WIDTH, AtomicBomber.HEIGHT);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();
        skin = game.skin;

        muteButton = new TextButton("Mute", game.skin);
        muteButton.setSize(200, 60);
        muteButton.setPosition(1200, 1000);

        backButton = new TextButton("Back", game.skin);
        backButton.setSize(200, 60);
        backButton.setPosition(1200, 200);


        stage.addActor(backButton);
        stage.addActor(muteButton);
    }

    @Override
    public void show() {
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new GameScreen(game));
            }
        });
        muteButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
    }

    @Override
    public void render(float v) {

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
