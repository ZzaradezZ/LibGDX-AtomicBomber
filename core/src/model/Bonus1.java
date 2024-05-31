package model;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.AtomicBomber;

public class Bonus1 {
    Texture texture;
    public static int HEIGHT = 45;
    public static int WIDTH = 45;
    private static final int SPEED = 80;
    float x, y;
    public boolean remove;
    public CollisionRect collisionRect;


    public Bonus1(float x, float y) {
        texture = new Texture("game/bonuscluster.png");
        remove = false;
        this.x = x;
        this.y = y;
        collisionRect = new CollisionRect(WIDTH, HEIGHT, x, y);
    }

    public void update(float delta) {
        y += delta * SPEED;
        collisionRect.move(x, y);
    }

    public void render(AtomicBomber game) {
        game.batch.draw(texture, x, y, WIDTH, HEIGHT);
    }




}
