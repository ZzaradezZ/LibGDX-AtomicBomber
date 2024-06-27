package model;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.AtomicBomber;

public class Jip {
    public static int HEIGHT = 80;
    public static int WIDTH = 170;
    private static Texture texture;
    private static boolean textureNull = false;
    public CollisionRect collisionRect;
    public static float DEFAULT_Y = 324;
    public float x;
    public boolean remove;
    private float statetime;
    private int flagAnimation;
    public static boolean frozen = false;

    public Jip(float x) {
        this.x = - WIDTH;
        if (texture == null)
            texture = new Texture("game/truck.png");
        collisionRect = new CollisionRect(WIDTH, HEIGHT - 80, x, DEFAULT_Y);
        remove = false;
    }

    public void update(float v) {
        if (!frozen) {
            statetime += v;
            x += 200 * v;
            if (x > AtomicBomber.WIDTH) {
                x = -1 * WIDTH;
            }
            collisionRect.move(x, 324);
        }
    }

    public void render(AtomicBomber game) {
        game.batch.draw(texture, x, DEFAULT_Y, WIDTH, HEIGHT);
    }
}
