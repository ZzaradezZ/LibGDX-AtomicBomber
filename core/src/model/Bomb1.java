package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bomb1 {
    public static final int ACCELERATION = 9;
    public int SPEED_Y = 50;
    public int SPEED_X = -50;
    public static int HEIGHT = 36;
    public static int WIDTH = 18;
    private static Texture texture;
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
    public Bomb1(float x, float y, int SPEED_X) {
        this.x = x;
        this.y = y;
        SPEED_Y = 50;
        this.SPEED_X = SPEED_X;
        statetime = 0f;
        flagAnimation = 0;
        if (texture == null) {
            texture = new Texture("game/bomb1.png");
        }
        sprite = new Sprite(texture);
        sprite.setOriginCenter();
        sprite.setOriginBasedPosition(10, 10);
        if (Bomber.getBomber().direction_X) {
            this.SPEED_X *= -1;
            sprite.rotate(30f);
            direction = true;
        } else {
            sprite.rotate(-30f);
            direction = false;
        }
        collisionRect = new CollisionRect(WIDTH, HEIGHT, x, DEFAULT_Y);
        remove = false;
    }

    public void update(float delta) {
        y -= SPEED_Y * delta;
        SPEED_Y += ACCELERATION;
        x += SPEED_X * delta;
        if (direction) {
            sprite.rotate(-0.2f);
        } else {
            sprite.rotate(0.2f);
        }
        if (y <= 324) {
            remove = true;
            blast = true;
        }
        collisionRect.move(x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(sprite, x, y, WIDTH, HEIGHT);
    }
}
