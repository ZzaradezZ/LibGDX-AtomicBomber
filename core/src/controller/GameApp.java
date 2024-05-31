package controller;

import com.mygdx.game.AtomicBomber;
import model.Building;
import model.Fort;
import model.Tank;
import model.Tree;
import view.GameScreen;

import java.util.Random;

public class GameApp {
    public static int tankNumber;
    public static int killedTank;
    public static int jipNumber;
    public static int killedJip;
    public static int treeNumber;
    public static int buildingNumber;
    public static int migNumber;
    public static int fortNumber;
    private static Random random = new Random();
    private static float statetime;
    public static int musicIndex = 0;


    public static void startWave1(AtomicBomber game, GameScreen gameScreen) {
        tankNumber = 3;
        killedTank = tankNumber;
        jipNumber = 6;
        treeNumber = 2;
        buildingNumber = 1;
        killedJip = jipNumber;
        migNumber = 0;
        statetime = 0;
        fortNumber = 1;
        startWave(game, gameScreen);
    }

    public static void startWave2(AtomicBomber game, GameScreen gameScreen) {
        tankNumber = 5;
        killedTank = tankNumber;
        jipNumber = 8;
        killedJip = jipNumber;
        treeNumber = 3;
        buildingNumber = 1;
        migNumber = 0;
        fortNumber = 2;
        statetime = 0;
        startWave(game, gameScreen);
    }

    public static void startWave3(AtomicBomber game, GameScreen gameScreen) {
        tankNumber = 6;
        killedTank = tankNumber;
        jipNumber = 10;
        treeNumber = 6;
        killedJip = jipNumber;
        buildingNumber = 2;
        migNumber = 15;
        statetime = 0;
        fortNumber = 2;
        startWave(game, gameScreen);
    }

    public static void startWave(AtomicBomber game, GameScreen gameScreen) {
        for (int i = 0; i < buildingNumber; i++) {
            gameScreen.buildings.add(new Building(random.nextInt(0, (AtomicBomber.WIDTH - Building.WIDTH) / 2)));
        }
        for (int i = 0; i < treeNumber; i++) {
            gameScreen.trees.add(new Tree(random.nextInt((AtomicBomber.WIDTH - Tree.WIDTH) / 2 , AtomicBomber.WIDTH - Tree.WIDTH)));
        }
        for (int i = 0; i < treeNumber; i++) {
            gameScreen.forts.add(new Fort(random.nextInt(AtomicBomber.WIDTH - Fort.WIDTH)));
        }
    }

    public static void decrease(int input) {
        switch (input) {
            case 0:
                jipNumber--;
                break;
            case 1:
                tankNumber--;
                break;
            case 2:
                migNumber--;
                break;
        }
    }

    public static void finish(AtomicBomber game) {

    }
}
