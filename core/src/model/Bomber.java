package model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.AtomicBomber;
import jdk.jfr.internal.tool.PrettyWriter;
import view.GameScreen;

import java.awt.*;
import java.util.ArrayList;

public class Bomber {
    public static final int SIZE = 32;
    private static int SPEED = 500;
    private static int SPEED_Y = 200;
    public static int HEIGHT = 30;
    public static int WIDTH = 140;
    public static int kills  = 0;
    private static Texture[] texture = new Texture[6];
    private static Texture[] fires = new Texture[3];
    private static int index = 0;
    private static int indexAnim = 0;
    public CollisionRect collisionRect;
    public int fireIndex;
    private float statetime;
    Animation animation = null;
    Sprite[][] sprite = new Sprite[2][3];
    private float rotate = 0f;
    private float addDegree = 100f;
    private static final float ANIM_TIME = 0.5f;
    public boolean direction_X;
    public int radioActiveNum;
    public int clusterNum;
    public float x, y;
    private int negative = 1;
    public static final float FRAME_LENGTH = 0.2f;
    private ArrayList<TextureRegion> regions = new ArrayList<>();
    public boolean cheatHP;
    private static Bomber bomber;
    public boolean explode;
    public float fireTime;
    public boolean destroyed;

    public Bomber(float x, float y) {
        cheatHP = false;
        fireTime = 0;
        fireIndex = 0;
        explode = false;
        this.x = x;
        this.y = y;
        statetime = 0;
        radioActiveNum = 0;
        clusterNum = 0;
        texture[0] = new Texture("game/warthog1.png");
        texture[1] = new Texture("game/warthog1f.png");
        texture[2] = new Texture("game/warthog2.png");
        texture[3] = new Texture("game/warthog2f.png");
        texture[4] = new Texture("game/warthog3.png");
        texture[5] = new Texture("game/warthog3f.png");
        fires[0] = new Texture("game/fire1.png");
        fires[1] = new Texture("game/fire2.png");
        fires[2] = new Texture("game/fire3.png");
//        texture[0] = new Texture("game/war1.png");
//        texture[1] = new Texture("game/war1f.png");
//        texture[2] = new Texture("game/war2.png");
//        texture[3] = new Texture("game/war2f.png");
        sprite[0][0] = new Sprite(texture[0]);
        sprite[1][0] = new Sprite(texture[1]);
        sprite[0][1] = new Sprite(texture[2]);
        sprite[1][1] = new Sprite(texture[3]);
        sprite[0][2] = new Sprite(texture[4]);
        sprite[1][2] = new Sprite(texture[5]);
        destroyed = false;

        bomber = this;
        collisionRect = new CollisionRect(WIDTH, HEIGHT, x, y);
    }

    public void update(float deltaTime, boolean direction, boolean directionS, boolean directionW, AtomicBomber game) {
        direction_X = direction;
        statetime += deltaTime;

        fireTime += deltaTime;
        if (fireTime > 0.5f) {
            fireIndex++;
            fireTime = 0;
            if (fireIndex == 3) {
                if (explode) {
                    if (!bomber.cheatHP) {
                        GameScreen.gameOver = true;
                        destroyed = true;
                    }
                    bomber.cheatHP = false;

                }
                explode = false;
                fireIndex = 0;
            }
        }
        if (direction) {
            negative = 1;
            index = 0;
            if (x > AtomicBomber.WIDTH + 50 - WIDTH) {
                x = 0;
            }
        } else {
            negative = -1;
            index = 1;
            if (x < -50) {
                x = AtomicBomber.WIDTH;
            }
        }
        x += negative * deltaTime * SPEED;

        if (directionS) {
            y -= SPEED_Y * deltaTime;
            if (y <= 400) {
                y = 400;
            }

            indexAnim = 1;
            rotate += addDegree;
            sprite[0][0].rotate(addDegree);
            sprite[1][0].rotate(addDegree);
        } else if (directionW) {
            y += SPEED_Y * deltaTime;
            if (y >= AtomicBomber.HEIGHT - HEIGHT) {
                y = AtomicBomber.HEIGHT - HEIGHT;
            }
            indexAnim = 2;
            rotate -= addDegree;
            sprite[0][0].rotate(-addDegree);
            sprite[1][1].rotate(-addDegree);
            indexAnim = 0;
            sprite[index][0].setRotation(rotate);
        }
        if (!directionS && directionW) {
            if (rotate > 0) {
                sprite[0][0].rotate(-addDegree);
            } else {
                sprite[0][0].rotate(addDegree);
            }
        }

        collisionRect.move(x, y);

        render(game.batch);
    }

    public void render(SpriteBatch batch) {
        batch.draw(sprite[index][0], x, y, WIDTH, HEIGHT);

        if (explode) {
            if (fireIndex != 3) {
                batch.draw(fires[fireIndex], x + 60, y, 100, 100);
            }
        }
    }

    public static Bomber getBomber() {
        return bomber;
    }

}
