package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion {
    public static final int SIZE = 32;
    public static final int OFFSET = 10;
    public boolean remove = false;
    float x, y;
    public float stateTime = 0;
    Animation animation = null;
    private TextureRegion[][] regions = new TextureRegion[1][2];
    public static final float FRAME_LENGTH = 0.2f;

    public Explosion(float x, float y) {
        this.x = x - OFFSET;
        this.y = y - OFFSET;
        animation = new Animation<>(FRAME_LENGTH, TextureRegion.split( new Texture("game/explosion.png"), SIZE, SIZE)[0]);
    }
    public void update(float deltaTime) {
        stateTime += deltaTime;
        if (animation.isAnimationFinished(stateTime)) remove = true;
    }
    public void render(SpriteBatch batch) {
        batch.draw((TextureRegion) animation.getKeyFrame(stateTime, true), x, y, 100, 100);
    }
}
