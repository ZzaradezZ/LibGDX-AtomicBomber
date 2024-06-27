package model;

public class GameInfo {
    public boolean guest;
    private int wave;
    public int kills;
    public static int musicIndex = 0;

    public GameInfo(boolean guest) {
        this.guest = guest;
        wave = 1;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public int getWave() {
        return wave;
    }

    public void addWave() {
        wave++;
    }
}
