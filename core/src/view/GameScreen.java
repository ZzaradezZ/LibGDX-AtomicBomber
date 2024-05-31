package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.AtomicBomber;
import controller.GameApp;
import model.*;
import model.Tree;

import javax.swing.plaf.SpinnerUI;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen implements Screen {
    public String[] musicPaths = new String[3];
    AtomicBomber game;
    Texture blank;
    public static Music music;
    public Music lastMusic;
    public Music music2;
    public Music music3;
    private Sprite sprite;
    private Stage stage;
    private Table table;
    private Skin skin;
    private float statetime;
    private float frozenBar;
    private GameInfo gameInfo;
    public boolean musicPause;
    private Random random = new Random();
    public boolean directionOfBomberX = true;
    public boolean directionOfBomberS = false;
    public boolean directionOfBomberW = false;
    private float x, y;
    private float frozenTime;
    private float migBulletTimer;
    private float tankBulletTimer;
    private boolean migSpawn;
    private float migSpawnTimer;
    private int migWarn_x;
    private float waveTimer;
    private boolean waveShow;
    private Window pauseWindow;
    private boolean pause;
    private boolean waveLabelAddStage;
    public static boolean gameOver;
    private Window gameOverWindow;
    private Label accuracyGameover;
    private Label lastWaveGameover;
    private Label killsGameover;
    private int lastWave;

    Sprite killSprite;
    Sprite nukeiconSprite;
    private Texture killIcon;
    private Texture nukeIcon;
    private Label showWaveLabel;
    private Label accuracyLabel;

    private static final float MIN_SPAWN_TANK = 4f;
    private static final float MAX_SPAWN_TANK = 12f;
    private static final float MIN_SPAWN_JIP = 3f;
    private static final float MAX_SPAWN_JIP = 8f;
    private static final float MIN_SPAWN_MIG = 1f;
    private static final float MAX_SPAWN_MIG = 4f;
    private static final int SPEED_X_BOMB = -120;

    public float tankTimer;
    public float jipTimer;
    public float migTimer;
    public int remainingTank;
    public int remainingJip;
    public int remainingMig;
    Bomber bomber;

    public ArrayList<Tank> tanks = new ArrayList<>();
    public ArrayList<Fort> forts = new ArrayList<>();
    public ArrayList<Jip> jips = new ArrayList<>();
    public ArrayList<Tree> trees = new ArrayList<>();
    public ArrayList<Mig> migs = new ArrayList<>();
    public ArrayList<Building> buildings = new ArrayList<>();
    public ArrayList<Explosion> explosions = new ArrayList<>();
    public ArrayList<Bomb1> bombs = new ArrayList<>();
    public ArrayList<Bomb1> bombsToRemove = new ArrayList<>();
    public ArrayList<Tank> tanksToRemove = new ArrayList<>();
    public ArrayList<Mig> migsToRemove = new ArrayList<>();
    public ArrayList<Jip> jipsToRemove = new ArrayList<>();
    public ArrayList<Tree> treesToRemove = new ArrayList<>();
    public ArrayList<Fort> fortsToRemove = new ArrayList<>();
    public ArrayList<Building> buildingsToRemove = new ArrayList<>();
    public ArrayList<Explosion> explosionsToRemove = new ArrayList<>();
    public ArrayList<Bonus> bonuses = new ArrayList<>();
    public ArrayList<Bonus1> bonus1s = new ArrayList<>();
    public ArrayList<RadioActive> radioActives = new ArrayList<>();
    public ArrayList<BlastBomb> blastBombs = new ArrayList<>();
    public ArrayList<BlastRadioAvtive> blastRadioAvtives = new ArrayList<>();
    public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    public static boolean mute;

    private Label killLabel;
    private Label nukeLabel;
    private Label clusterLabel;
    private Label waveLabel;
    private final TextButton exitButton;
    private final TextButton settingButton;
    private Texture freeze;
    private Texture clusterIcon;
    private Texture migWarning;
    private int bombsFired;

    public GameScreen(AtomicBomber game) {
        musicPause = false;
        musicPaths[0] = "0.mp3";
        musicPaths[1] = "1.mp3";
        musicPaths[2] = "2.mp3";
        gameOver = false;
        pause = false;
        waveShow = false;
        bombsFired = 0;
        waveTimer = 0;
        migSpawnTimer = 0f;
        remainingTank = 0;
        remainingJip = 0;
        frozenTime = 0f;
        migBulletTimer = 0f;
        tankBulletTimer = 0f;
        killIcon = new Texture("game/killIcon.png");
        nukeIcon = new Texture("game/nukeicon.png");
        clusterIcon = new Texture("game/clustericon.png");
        migWarning = new Texture("game/migwarning.png");
        migSpawn = false;
        migWarn_x = 700;
        killSprite = new Sprite(killIcon);
        nukeiconSprite = new Sprite(nukeIcon);
        killSprite.setPosition(0, 1200);
        killSprite.setSize(50, 50);
        nukeiconSprite.setPosition(0, 1100);
        nukeiconSprite.setSize(50, 50);
        freeze = new Texture("game/freeze.png");
        this.game = game;
        sprite = new Sprite();
        Texture backgroundTexture = new Texture("menu/background2.png");
        blank = new Texture("game/blank.png");
        this.sprite = new Sprite(backgroundTexture);
        this.sprite.setSize(AtomicBomber.WIDTH, AtomicBomber.HEIGHT);
        skin = game.skin;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        frozenBar = 0f;
        settingButton = new TextButton("Setting", skin);
        settingButton.setSize(150, 80);
        settingButton.setPosition(2360 + 50, 1340 - 80);
        exitButton = new TextButton("EXIT", skin);
        exitButton.setSize(200, 100);
        exitButton.setColor(Color.RED);
        exitButton.setPosition(2360, 1340);
        stage.addActor(exitButton);
        stage.addActor(settingButton);
        waveLabelAddStage = false;
        gameInfo = game.gameInfo;
        tankTimer = random.nextFloat() * (MAX_SPAWN_TANK - MIN_SPAWN_TANK) + MIN_SPAWN_TANK;
        migTimer = random.nextFloat() * (MAX_SPAWN_MIG - MIN_SPAWN_MIG) + MIN_SPAWN_MIG;
        jipTimer = random.nextFloat() * (MAX_SPAWN_JIP - MIN_SPAWN_JIP) + MIN_SPAWN_JIP;
        bomber = new Bomber(2560 / 2, (1440 / 2) + 400);
        killLabel = new Label("kills : " + Bomber.kills, skin);
        nukeLabel = new Label("nuke : " + bomber.radioActiveNum, skin);
        clusterLabel = new Label("clusters :", skin);
        waveLabel = new Label("wave :", skin);
        if (gameInfo.getWave() == 1) {
            GameApp.startWave1(game, this);
        } else if (gameInfo.getWave() == 2) {
            GameApp.startWave2(game, this);
        } else if (gameInfo.getWave() == 3) {
            GameApp.startWave3(game, this);
        } else if (gameInfo.getWave() == 4) {
            GameApp.finish(game);
        }
        if (gameInfo.getWave() == 1) {
            System.out.println(GameInfo.musicIndex);
            if (music == null)
                music = Gdx.audio.newMusic(Gdx.files.internal(musicPaths[0]));
            music.play();
            music.setVolume(1f);
        }
        killLabel.setFontScale(2.4f);
        nukeLabel.setFontScale(2.4f);
        waveLabel.setFontScale(2.4f);
        clusterLabel.setFontScale(2.4f);
        killLabel.setSize(100, 50);
        nukeLabel.setSize(100, 50);
        killLabel.setPosition(100, 1200);
        nukeLabel.setPosition(100, 1100);
        waveLabel.setPosition(0, 900);
        clusterLabel.setPosition(100, 1000);
        clusterLabel.setSize(100, 50);
        waveLabel.setSize(100, 50);
        if (gameInfo.getWave() == 1) {
            Bomber.kills = 0;
        }
        if (mute) {
            music.setVolume(0f);
        } else {
            music.setVolume(1f);
        }

        killLabel.setColor(Color.BLACK);
        nukeLabel.setColor(Color.BLACK);
        waveLabel.setColor(Color.BLACK);
        clusterLabel.setColor(Color.BLACK);
        stage.addActor(killLabel);
        stage.addActor(nukeLabel);
        stage.addActor(clusterLabel);
        stage.addActor(waveLabel);

        showWaveLabel = new Label("wave " + gameInfo.getWave() + " finished", skin);
        showWaveLabel.setColor(Color.BLACK);
        showWaveLabel.setSize(400, 180);
        showWaveLabel.setPosition(2560 / 2 - 200, 1440 / 2 - 90);
        showWaveLabel.setFontScale(5f);
        accuracyLabel = new Label("accuracy : " + (float) Bomber.kills / bombsFired, skin);
        accuracyLabel.setFontScale(4f);
        accuracyLabel.setColor(Color.BLACK);
        accuracyLabel.setSize(400, 180);
        accuracyLabel.setPosition(2560 / 2 - 200, 1440 / 2 - 290);
    }

    @Override
    public void show() {
        exitButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                music.stop();
                if (game.gameInfo.guest) {
                    User.saveUsers();
                    dispose();
                    game.setScreen(new RegisterMenu(game));
                } else {
                    User.saveUsers();
                    dispose();
                    game.setScreen(new MainMenu(game));
                }
            }
        });

        settingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pauseWindow = new Window("Game Paused", skin);
                pauseWindow.setResizable(false);
                pauseWindow.setSize(600, 600);
                pauseWindow.setPosition(AtomicBomber.WIDTH / 2 - pauseWindow.getWidth() / 2, AtomicBomber.HEIGHT / 2 - pauseWindow.getHeight() / 2);

                pause = true;
                TextButton resumeButton = new TextButton("Resume", skin);
                TextButton changeMusicButton = new TextButton("Change Music", skin);
                TextButton stopMusic = new TextButton("pause music", skin);
                TextButton saveAndExitButton = new TextButton("Exit With Saving", skin);
                TextButton exitWithoutSavingButton = new TextButton("Exit Without Saving", skin);
                pauseWindow.add(resumeButton);
                pauseWindow.row();
                pauseWindow.add(stopMusic);
                pauseWindow.row();
                pauseWindow.add(changeMusicButton);
                pauseWindow.row();
                pauseWindow.add(saveAndExitButton);
                pauseWindow.row();
                pauseWindow.add(exitWithoutSavingButton);
                stage.addActor(pauseWindow);
                resumeButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        pause = false;
                        pauseWindow.remove();
                    }
                });
                stopMusic.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (musicPause) {
                            music.play();
                            musicPause  = false;
                        } else {
                            music.pause();
                            musicPause = true;
                        }
                    }
                });
                exitWithoutSavingButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        music.stop();
                        dispose();
                        game.setScreen(new RegisterMenu(game));
                    }
                });
                changeMusicButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        music.stop();
                        GameInfo.musicIndex++;
                        if (GameInfo.musicIndex == 3) {
                            GameInfo.musicIndex = 0;
                        }
                        music = Gdx.audio.newMusic(Gdx.files.internal(musicPaths[GameInfo.musicIndex]));
                        music.play();
                    }
                });
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        if (pause) {
            sprite.draw(game.batch);
            game.batch.draw(blank, 0, 0, frozenBar * 2560, 40);
            game.batch.draw(clusterIcon, 0, 1000, 80, 50);
            game.batch.draw(killIcon, 0, 1200, 80, 50);
            game.batch.draw(nukeIcon, 0, 1100, 80, 50);
            if (migSpawn) {
                game.batch.draw(migWarning, 150f, migWarn_x, 100f, 100f);
            }
            if (Tank.frozen) {
                game.batch.draw(freeze, (float) -600 / 2, (float) -270 / 2, AtomicBomber.WIDTH + 600, AtomicBomber.HEIGHT + 270);
            }
            for (Tank tank : tanks) {
                tank.render(game);
            }
            for (Mig mig : migs) {
                mig.render(game);
            }
            for (Jip jip : jips) {
                jip.render(game);
            }
            for (Tree tree : trees) {
                tree.render(game.batch);
            }
            for (Building building : buildings) {
                building.render(game.batch);
            }
            for (Fort fort : forts) {
                fort.render(game.batch);
            }
            bomber.render(game.batch);

        } else {

            tankTimer -= delta;
            jipTimer -= delta;
            migTimer -= delta;
            frozenTime += delta;

            if (tankTimer < 0 && GameApp.tankNumber > 0) {
                tankTimer = random.nextFloat() * (MAX_SPAWN_TANK - MIN_SPAWN_TANK) + MIN_SPAWN_TANK;
                tanks.add(new Tank(-Tank.WIDTH));
                GameApp.decrease(1);
            }
            if (jipTimer < 0 && GameApp.jipNumber > 0) {
                jipTimer = random.nextFloat() * (MAX_SPAWN_JIP - MIN_SPAWN_JIP) + MIN_SPAWN_JIP;
                jips.add(new Jip(random.nextInt(AtomicBomber.WIDTH - Jip.WIDTH)));
                GameApp.decrease(0);
            }
            if (migTimer < 0 && GameApp.migNumber > 0 && !migSpawn) {
                migSpawn = true;

                migWarn_x = random.nextInt(500, AtomicBomber.HEIGHT - Mig.HEIGHT);
                migSpawnTimer = 0;
            }
            if (migSpawn) {
                migSpawnTimer += delta;
                if (migSpawnTimer > 2f && GameApp.migNumber > 0) {
                    migTimer = random.nextFloat() * (MAX_SPAWN_MIG - MIN_SPAWN_MIG) + MIN_SPAWN_MIG;
                    migs.add(new Mig((float) migWarn_x));
                    GameApp.decrease(2);
                    migSpawn = false;
                }
            }
            sprite.draw(game.batch);
            if (frozenBar > 0.91f) {
                game.batch.setColor(Color.BLUE);
            } else if (frozenBar > 0.6f) game.batch.setColor(Color.WHITE);
            else game.batch.setColor(Color.DARK_GRAY);
            if (Gdx.input.isKeyPressed(Input.Keys.TAB) && frozenBar < 0.91f) {
                game.batch.setColor(Color.RED);
            }
            game.batch.draw(blank, 0, 0, frozenBar * 2560, 40);

            game.batch.setColor(Color.WHITE);
            statetime += delta;


            directionOfBomberS = false;
            directionOfBomberW = false;
            if (Gdx.input.isKeyJustPressed(Input.Keys.D) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                directionOfBomberX = true;
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.A) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                directionOfBomberX = false;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                directionOfBomberS = true;
            } else if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
                directionOfBomberW = true;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                bombs.add(new Bomb1(bomber.x, bomber.y, SPEED_X_BOMB));
                bombsFired++;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
                if (bomber.clusterNum != 0) {
                    bomber.clusterNum--;
                    bombs.add(new Bomb1(bomber.x - 5, bomber.y, SPEED_X_BOMB));
                    bombs.add(new Bomb1(bomber.x, bomber.y, 0));
                    bombs.add(new Bomb1(bomber.x + 5, bomber.y, -1 * SPEED_X_BOMB));
                }
                bombsFired += 3;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.TAB)) {
                if (frozenBar > 0.91f) {
                    Tank.frozen = true;
                    Jip.frozen = true;
                    Mig.frozen = true;
                    frozenTime = 0;
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                if (bomber.radioActiveNum != 0) {
                    bomber.radioActiveNum--;
                    radioActives.add(new RadioActive(bomber.x, bomber.y, 0));
                }
                bombsFired++;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
                bomber.radioActiveNum++;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                if (gameInfo.getWave() == 3) {
                    gameOver = true;
                } else {
                    waveShow = true;
                    waveTimer = 0;
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)) {
                bomber.clusterNum++;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
                tanks.add(new Tank(random.nextInt(AtomicBomber.WIDTH - 500)));
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.H)) {
                bomber.cheatHP = true;
            }
            if (gameOver) {
                music.setVolume(0);
                lastWave = gameInfo.getWave();
                gameInfo.setWave(1);
                pause = true;
                String title;
                if (bomber.destroyed) {
                    title = "LOSE";
                } else {
                    title = "WIN";
                }
                gameOverWindow = new Window(title, skin);
                gameOverWindow.setResizable(false);
                gameOverWindow.setSize(800, 400);
                gameOverWindow.setPosition(AtomicBomber.WIDTH / 2 - gameOverWindow.getWidth() / 2, AtomicBomber.HEIGHT / 2 - gameOverWindow.getHeight() / 2);
                TextButton endGameButton = new TextButton("Return to main menu", skin);
                gameOverWindow.add(endGameButton);
                endGameButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        music.stop();
                        stage.dispose();

                        game.setScreen(new MainMenu(game));
                    }
                });
                killsGameover = new Label("kills : " + Bomber.kills + "  ", skin);
                lastWaveGameover = new Label("last wave : " + lastWave + " | ", skin);
                float accuracyValueGameover = (float) Bomber.kills / bombsFired;
                if (bombsFired == 0) {
                    accuracyValueGameover = 0;
                }
                accuracyGameover = new Label("  accuracy : " + accuracyValueGameover + " | ", skin);
                gameOverWindow.add(accuracyGameover);
                gameOverWindow.add(lastWaveGameover);
                gameOverWindow.add(killsGameover);
                stage.addActor(gameOverWindow);
            }

            if (frozenTime > 3f && Tank.frozen) {
                Tank.frozen = false;
                Jip.frozen = false;
                Mig.frozen = false;
                frozenTime = 0;
                frozenBar = 0f;
            }


            bomber.update(delta, directionOfBomberX, directionOfBomberS, directionOfBomberW, game);
            for (Tank tank : tanks) {
                tank.render(game);
                tank.update(delta);
                if (tank.remove) {
                    tanksToRemove.add(tank);
                }
                float shib = ((float) (Bomber.getBomber().y - 324)) / ((float) (Bomber.getBomber().x - tank.x));
                if (shib < 0) {
                    shib *= -1;
                }
                if (!Tank.frozen && gameInfo.getWave() != 1) {
                    if (shib < 1.2f && shib > 0.8f) {
                        if (migBulletTimer > 0.6f) {
                            bullets.add(new Bullet(tank.x + 30, 324, 400, 400));
                            migBulletTimer = 0;
                        }
                    }
                }
            }
            migBulletTimer += delta;
            tankBulletTimer += delta;
            for (Mig mig : new ArrayList<>(migs)) {
                mig.render(game);
                mig.update(delta);
                if (mig.remove)
                    migs.remove(mig);
                if (!Mig.frozen) {
                    if (Math.abs(Bomber.getBomber().y - mig.y) < 10) {
                        if (migBulletTimer > 0.6f) {
                            bullets.add(new Bullet(mig.x, mig.y, 800, 0));
                            migBulletTimer = 0;
                        }
                    }
                }
            }

            for (Jip jip : jips) {
                jip.render(game);
                jip.update(delta);
                if (jip.remove) {
                    jipsToRemove.add(jip);
                }
            }
            for (Tree tree : trees) {
                tree.render(game.batch);
                if (tree.remove) {
                    treesToRemove.add(tree);
                }
            }
            for (Fort fort : forts) {
                fort.render(game.batch);
                if (fort.remove) {
                    fortsToRemove.add(fort);
                }
            }
            for (Building building : buildings) {
                building.render(game.batch);
                if (building.remove) {
                    buildingsToRemove.add(building);
                }
            }
            for (Bomb1 bomb : bombs) {
                bomb.update(delta);
                bomb.render(game.batch);
                if (bomb.remove) {
                    bombsToRemove.add(bomb);
                    if (bomb.blast) {
                        blastBombs.add(new BlastBomb(bomb.x, bomb.y));
                    }
                }
            }

            for (Bullet bullet : new ArrayList<>(bullets)) {
                bullet.update(delta);
                bullet.render(game.batch);
                if (bullet.remove) {
                    bullets.remove(bullet);
                }
                if (bullet.collisionRect.collidesWith(bomber.collisionRect)) {
                    Bomber.getBomber().explode = true;
                    Bomber.getBomber().fireIndex = 0;
                    Bomber.getBomber().fireTime = 0;
                    bullets.remove(bullet);
                }
            }

            for (RadioActive radioActive : new ArrayList<>(radioActives)) {
                radioActive.render(game.batch);
                radioActive.update(delta);
                if (radioActive.remove) {
                    radioActives.remove(radioActive);
                    if (radioActive.blast) {
                        blastRadioAvtives.add(new BlastRadioAvtive(radioActive.x, radioActive.y));
                    }
                }
            }

            for (Bomb1 bomb : bombs) {
                for (Tank tank : new ArrayList<>(tanks)) {
                    if (tank.collisionRect.collidesWith(bomb.collisionRect)) {
                        remainingTank++;
                        frozenBar += 0.2f;
                        Bomber.kills += 5;
                        explosions.add(new Explosion(tank.x, 344));
                        tanks.remove(tank);
                        if (!bombsToRemove.contains(bomb))
                            bombsToRemove.add(bomb);
                    }
                }
                for (Jip jip : new ArrayList<>(jips)) {
                    if (jip.collisionRect.collidesWith(bomb.collisionRect)) {
                        remainingJip++;
                        frozenBar += 0.1f;
                        Bomber.kills += 1;
                        explosions.add(new Explosion(jip.x, 344));
                        jips.remove(jip);
                        if (!bombsToRemove.contains(bomb))
                            bombsToRemove.add(bomb);
                    }
                }
                for (Tree tree : trees) {
                    if (tree.collisionRect.collidesWith(bomb.collisionRect)) {
                        frozenBar += 0.1f;
                        explosions.add(new Explosion(tree.x, 344));
                        treesToRemove.add(tree);
                        if (!bombsToRemove.contains(bomb))
                            bombsToRemove.add(bomb);
                    }
                }
                for (Fort fort : forts) {
                    if (fort.collisionRect.collidesWith(bomb.collisionRect)) {
                        frozenBar += 0.1f;
                        Bomber.kills += 1;
                        bonus1s.add(new Bonus1(fort.x, 324));
                        explosions.add(new Explosion(fort.x, 344));
                        fortsToRemove.add(fort);
                        if (!bombsToRemove.contains(bomb))
                            bombsToRemove.add(bomb);
                    }
                }
                for (Building building : buildings) {
                    if (building.collisionRect.collidesWith(bomb.collisionRect)) {
                        Bomber.kills += 2;
                        bonuses.add(new Bonus(building.x, 324));
                        explosions.add(new Explosion(building.x, 344));
                        buildingsToRemove.add(building);
                        if (!bombsToRemove.contains(bomb))
                            bombsToRemove.add(bomb);
                    }
                }
            }
            if (Tank.frozen) {
                game.batch.draw(freeze, (float) -600 / 2, (float) -270 / 2, AtomicBomber.WIDTH + 600, AtomicBomber.HEIGHT + 270);
            }
            if (migSpawn) {
                game.batch.draw(migWarning, 150f, migWarn_x, 100f, 100f);
            }
            for (Bonus bonus : bonuses) {
                if (bonus.collisionRect.collidesWith(Bomber.getBomber().collisionRect)) {
                    bonus.remove = true;
                    bomber.radioActiveNum++;
                }
            }
            for (BlastBomb blastBomb : new ArrayList<>(blastBombs)) {
                blastBomb.render(game.batch);
                blastBomb.update(delta);
                if (blastBomb.remove) {
                    blastBombs.remove(blastBomb);
                }
            }
            for (BlastRadioAvtive blastRadioAvtive : new ArrayList<>(blastRadioAvtives)) {
                blastRadioAvtive.render(game.batch);
                blastRadioAvtive.update(delta);
                if (blastRadioAvtive.boom) {
                    for (Tank tank : new ArrayList<>(tanks)) {
                        frozenBar += 0.2f;
                        Bomber.kills += 5;
                        explosions.add(new Explosion(tank.x, 344));
                        tanks.remove(tank);
                        remainingTank++;
                    }
                    for (Jip jip : new ArrayList<>(jips)) {
                        frozenBar += 0.1f;
                        Bomber.kills += 1;
                        explosions.add(new Explosion(jip.x, 344));
                        jips.remove(jip);
                        remainingJip++;
                    }
                    for (Tree tree : trees) {
                        frozenBar += 0.1f;
                        explosions.add(new Explosion(tree.x, 344));
                        treesToRemove.add(tree);
                    }
                    for (Fort fort : forts) {
                        frozenBar += 0.1f;
                        Bomber.kills += 1;
                        bonus1s.add(new Bonus1(fort.x, 324));
                        explosions.add(new Explosion(fort.x, 344));
                        fortsToRemove.add(fort);
                    }
                }
                if (blastRadioAvtive.remove) {
                    blastRadioAvtives.remove(blastRadioAvtive);
                }
            }
            for (Bonus1 bonus1 : bonus1s) {
                if (bonus1.collisionRect.collidesWith(Bomber.getBomber().collisionRect)) {
                    bomber.clusterNum++;
                    bonus1.remove = true;
                }
            }
            for (Explosion explosion : new ArrayList<>(explosions)) {
                explosion.render(game.batch);
                explosion.update(delta);
                if (explosion.remove) {
                    explosions.remove(explosion);
                }
            }
            for (Bonus bonus : new ArrayList<>(bonuses)) {
                bonus.render(game);
                bonus.update(delta);
                if (bonus.remove) {
                    bonuses.remove(bonus);
                }
            }
            for (Bonus1 bonus1 : new ArrayList<>(bonus1s)) {
                bonus1.render(game);
                bonus1.update(delta);
                if (bonus1.remove) {
                    bonus1s.remove(bonus1);
                }
            }
            game.batch.draw(clusterIcon, 0, 1000, 80, 50);
            game.batch.draw(killIcon, 0, 1200, 80, 50);
            game.batch.draw(nukeIcon, 0, 1100, 80, 50);
            killLabel.setText("kills : " + Bomber.kills);
            nukeLabel.setText("nukes : " + bomber.radioActiveNum);
            clusterLabel.setText("clusters : " + bomber.clusterNum);
            waveLabel.setText("wave :" + gameInfo.getWave());
            if (waveShow) {
                remainingTank = 0;
                remainingJip = 0;

                showWaveLabel.setText("wave " + gameInfo.getWave() + " finished.");
                float accuracy;
                if (bombsFired == 0) {
                    accuracy = 0f;
                } else {
                    accuracy = (float) Bomber.kills / bombsFired;
                }
                accuracyLabel.setText("accuracy : " + accuracy);
                if (!waveLabelAddStage) {
                    waveLabelAddStage = true;
                    stage.addActor(showWaveLabel);
                    stage.addActor(accuracyLabel);
                }
                pause = true;
            }
            tanks.removeAll(tanksToRemove);
            jips.removeAll(jipsToRemove);
            trees.removeAll(treesToRemove);
            forts.removeAll(fortsToRemove);
            buildings.removeAll(buildingsToRemove);
            bombs.removeAll(bombsToRemove);
            migs.removeAll(migsToRemove);
            explosions.removeAll(explosionsToRemove);
            tanksToRemove.clear();
            jipsToRemove.clear();
            treesToRemove.clear();
            fortsToRemove.clear();
            buildingsToRemove.clear();
            migsToRemove.clear();
            explosionsToRemove.clear();
            bombsToRemove.clear();
        }
        waveTimer += delta;
        if (GameApp.killedTank <= remainingTank && GameApp.killedJip <= remainingJip) {
            if (gameInfo.getWave() == 3) {
                gameOver = true;
            } else {
                waveShow = true;
                waveTimer = 0;
            }
        }
        if (waveTimer > 1f && waveShow) {
            gameInfo.addWave();
            showWaveLabel.setText("");
            accuracyLabel.setText("");
            stage.clear();
            game.setScreen(new GameScreen(game));
        }
        game.batch.end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        table.clear();
    }
}