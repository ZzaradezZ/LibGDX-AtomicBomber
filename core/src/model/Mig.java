package model;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.AtomicBomber;

public class Mig {
    public static int HEIGHT = 30;
    public static int WIDTH = 120;
    private static final int SPEED = 300;
    private static Texture texture;
    private static boolean textureNull = false;
    public CollisionRect collisionRect;
    public static float DEFAULT_Y = 324;
    public float x, y;
    public boolean remove;
    private float statetime;
    private int flagAnimation;
    public static boolean frozen = false;

    public Mig(float y) {
        this.x = - WIDTH;
        this.y = y;
        if (texture == null)
            texture = new Texture("game/mig1.png");
        collisionRect = new CollisionRect(WIDTH, HEIGHT - 80, x, DEFAULT_Y);
        remove = false;
    }

    public void update(float v) {
        if (!frozen) {
            statetime += v;
            x += SPEED * v;
            if (x > AtomicBomber.WIDTH) {
               this.remove = true;
            }
            collisionRect.move(x, 324);
        }
    }

    public void render(AtomicBomber game) {
        game.batch.draw(texture, x, y, WIDTH, HEIGHT);
    }
}
