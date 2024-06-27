package model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tree {
    public static int HEIGHT = 160;
    public static int WIDTH = 80;
    private static Texture texture;
    private static boolean textureNull = false;
    public CollisionRect collisionRect;
    public static float DEFAULT_Y = 324;
    public float x;
    public boolean remove;
    private float statetime;
    private int flagAnimation;

    public Tree(float x) {
        this.x = x;
        statetime = 0f;
        if (texture == null) {
            texture = new Texture("game/tree2.png");
        }
        collisionRect = new CollisionRect(WIDTH, HEIGHT, x, DEFAULT_Y);
        remove = false;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, DEFAULT_Y, WIDTH, HEIGHT);
    }
}
