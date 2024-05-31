package view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.AtomicBomber;

public class LeaderBoardScreen implements Screen {
    AtomicBomber game;
    private Sprite sprite;
    private Stage stage;
    private Skin skin;
    Texture background;

    public LeaderBoardScreen(AtomicBomber game) {
        this.game = game;
        background = new Texture("background.png");
        sprite = new Sprite(background);
    }

    @Override
    public void show() {

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
