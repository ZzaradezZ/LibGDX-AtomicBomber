package model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fort {
    public static int HEIGHT = 120;
    public static int WIDTH = 150;
    private static Texture[] texture = new Texture[2];
    private static boolean textureNull = true;
    public CollisionRect collisionRect;
    public static float DEFAULT_Y = 324;
    public float x;
    public boolean remove;
    private float statetime;
    private int flagAnimation = 0;

    public Fort(float x) {
        this.x = x;
        statetime = 0f;
        flagAnimation = 0;
        if (textureNull) {
            texture[0] = new Texture("game/fort2.png");
            texture[1] = new Texture("game/fort1.png");
            textureNull = false;
        }
        collisionRect = new CollisionRect(WIDTH, HEIGHT, x, DEFAULT_Y);
        remove = false;
    }

    public void render(SpriteBatch batch) {
        statetime += Gdx.graphics.getDeltaTime();
        if (statetime > 0.5f) {
            flagAnimation++;
            statetime = 0;
            if (flagAnimation == 2) {
                flagAnimation = 0;
            }
        }
        batch.draw(texture[flagAnimation], x, DEFAULT_Y, WIDTH, HEIGHT);
    }
}
