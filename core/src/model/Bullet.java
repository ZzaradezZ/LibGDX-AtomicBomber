package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jdk.jpackage.internal.LinuxDebBundler;

public class Bullet {
    public int SPEED_Y;
    public int SPEED_X;
    public static int HEIGHT = 10;
    public static int WIDTH = 45;
    private static Texture[] texture = new Texture[3];
    private static Texture bomb;
    private Sprite sprite;
    private static boolean textureNull = false;
    public CollisionRect collisionRect;
    public static float DEFAULT_Y = 324;
    public float x, y;
    public boolean remove;
    private float statetime;
    private int flagAnimation;
    private boolean direction;
    public boolean blast = false;
    private int index;
    private boolean isBomb;

    public Bullet(float x, float y, int SPEED_X, int SPEED_Y) {
        this.x = x;
        if (SPEED_X < 800) {
            isBomb = true;
        }
        this.y = y - 5;
        index = 0;
        this.SPEED_Y = SPEED_Y;
        this.SPEED_X = SPEED_X;
        statetime = 0f;
        flagAnimation = 0;
        bomb = new Texture("game/bomb.png");
        texture[0] = new Texture("game/sam.png");
        texture[1] = new Texture("game/sam1.png");
        texture[2] = new Texture("game/sam2.png");
        collisionRect = new CollisionRect(WIDTH, HEIGHT, x, DEFAULT_Y);
        remove = false;
    }

    public void update(float delta) {
        x += SPEED_X * delta;
        y += SPEED_Y * delta;
        statetime += delta;
        if (x >= 2560) {
            remove = true;
        }
        collisionRect.move(x, y);
        if (statetime > 0.6f) {
            index++;
            if (index == 3)
                index = 0;
        }
    }

    public void render(SpriteBatch batch) {
        if (!isBomb) {
            batch.draw(texture[index], x, y, WIDTH, HEIGHT);
        } else {
            batch.draw(bomb, x, y, 17, 17);
        }

    }
}