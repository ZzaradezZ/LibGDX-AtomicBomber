package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RadioActive {
    public static final int ACCELERATION = 4;
    public int SPEED_Y = 50;
    public int SPEED_X = -50;
    public static int HEIGHT = 100;
    public static int WIDTH = 50;
    private static Texture[] texture = new Texture[6];
    private Sprite sprite;
    private static boolean textureNull = false;
    public CollisionRect collisionRect;
    public static float DEFAULT_Y = 324;
    public float x, y;
    public boolean remove;
    private float statetime;
    private int flagAnimation;
    private boolean direction;
    private int index;
    public boolean blast = false;

    public RadioActive(float x, float y, int SPEED_X) {
        this.x = x;
        this.y = y;
        SPEED_Y = 50;
        this.SPEED_X = SPEED_X;
        statetime = 0f;
        flagAnimation = 0;
        index = 0;
        texture[0] = new Texture("game/rocket1.png");
        texture[1] = new Texture("game/rocket2.png");
        texture[2] = new Texture("game/rocket3.png");
        texture[3] = new Texture("game/rocket4.png");
        texture[4] = new Texture("game/rocket5.png");
        texture[5] = new Texture("game/rocket6.png");
        if (Bomber.getBomber().direction_X) {
            this.SPEED_X *= -1;
//            sprite.rotate(30f);
            direction = true;
        } else {
//            sprite.rotate(-30f);
            direction = false;
        }
        collisionRect = new CollisionRect(WIDTH, HEIGHT, x, DEFAULT_Y);
        remove = false;
    }

    public void update(float delta) {
        y -= SPEED_Y * delta;
        SPEED_Y += ACCELERATION;
        x += SPEED_X * delta;
        if (y <= 324) {
            remove = true;
            blast = true;
        }
        statetime += delta;
        if (statetime > 0.1f) {
            statetime = 0;
            index++;
            if (index == 6) {
                index = 0;
            }
        }
        collisionRect.move(x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture[index], x, y, WIDTH, HEIGHT);
    }
}
