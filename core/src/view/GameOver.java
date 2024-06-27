package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.AtomicBomber;

public class GameOver implements Screen {
    AtomicBomber game;
    private Sprite sprite;
    private Stage stage;
    private Skin skin;
    Texture backgroundTexture;
    Label winLabel;
    Label accuracyLabel;



    public GameOver(AtomicBomber game) {
        this.game = game;
        this.game = game;
        backgroundTexture = new Texture("menu/background2.png");
        sprite = new Sprite(backgroundTexture);
        sprite.setSize(AtomicBomber.WIDTH, AtomicBomber.HEIGHT);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = game.skin;




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

    }
}
