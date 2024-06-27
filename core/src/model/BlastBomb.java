package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BlastBomb {
    public static final int SIZE = 32;
    public static final int OFFSET = 10;
    public boolean remove = false;
    float x, y;
    public float stateTime = 0;
    private int index;
    Animation animation = null;
    public static Texture[] textures = new Texture[3];
    private TextureRegion[][] regions = new TextureRegion[1][2];
    public static final float FRAME_LENGTH = 0.2f;

    public BlastBomb(float x, float y) {
        this.x = x - OFFSET;
        index = 0;
        this.y = y - OFFSET;
        remove = false;
        textures[0] = new Texture("game/blast1.png");
        textures[1] = new Texture("game/blast2.png");
        textures[2] = new Texture("game/blast3.png");
    }
    public void update(float deltaTime) {
        stateTime += deltaTime;
        if (stateTime > 0.3f) {
            index++;
            stateTime = 0;
            if (index == 3) {
                remove = true;
            }
        }
    }
    public void render(SpriteBatch batch) {
        batch.draw(textures[index], x, y, 60, 60);
    }
}
