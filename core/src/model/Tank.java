package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AtomicBomber;

import java.awt.*;

public class Tank {
    private static final float SPEED = 50f;
    public static int HEIGHT = 120;
    public static int WIDTH = 220;
    private static Texture[] textures = new Texture[2];
    private static boolean textureNull = false;
    public CollisionRect collisionRect;
    public static float DEFAULT_Y = 324;
    public float x;
    public boolean remove;
    private float statetime;
    public static boolean frozen = false;
    private int index;

    public Tank(float x) {
        this.x = x;
        index = 0;
        textures[0] = new Texture("game/tank1.png");
        textures[1] = new Texture("game/tank2.png");
        remove = false;
        collisionRect = new CollisionRect(WIDTH, HEIGHT - 55, x, DEFAULT_Y);
    }

    public void update(float v) {
        if (frozen) {

        } else {
            statetime += v;
            x += 100 * v;
            if (x > AtomicBomber.WIDTH) {
                x = -1 * WIDTH;
            }
            collisionRect.move(x, 324);
            if (statetime > 0.3f) {
                index++;
                statetime = 0;
                if (index == 2) {
                    index = 0;
                }
            }
        }

    }

    public void render(AtomicBomber game) {
        game.batch.draw(textures[index], x, DEFAULT_Y, WIDTH, HEIGHT);
    }
}