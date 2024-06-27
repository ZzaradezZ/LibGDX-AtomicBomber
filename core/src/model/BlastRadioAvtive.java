package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BlastRadioAvtive {
    public static final int SIZE = 32;
    public static final int OFFSET = 10;
    public boolean remove = false;
    float x, y;
    public float stateTime = 0;
    private int index;
    Animation animation = null;
    public static Texture[] textures = new Texture[4];
    private TextureRegion[][] regions = new TextureRegion[1][2];
    public static final float FRAME_LENGTH = 0.2f;
    public boolean boom;

    public BlastRadioAvtive(float x, float y) {
        this.x = x - OFFSET;
        index = 0;
        this.y = y - OFFSET;
        remove = false;
        textures[0] = new Texture("game/bigblast1.png");
        textures[1] = new Texture("game/bigblast2.png");
        textures[2] = new Texture("game/bigblast3.png");
        textures[3] = new Texture("game/bigblast4.png");
        boom  = false;
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;
        if (stateTime > 0.3f) {
            index++;
            stateTime = 0;
            if (index == 4) {
                remove = true;
            }
        }
        if (index == 1) {
            boom = true;
        }
    }

    public void render(SpriteBatch batch) {
        if (index != 4)
            batch.draw(textures[index], x, y - 15, 220, 220);
    }
}
