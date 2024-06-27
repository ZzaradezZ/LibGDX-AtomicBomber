package model;

public class CollisionRect {
    int width , height;
    float x, y;

    public CollisionRect(int width, int height, float x, float y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }
    public void move(float x, float y) {
        this.y = y;
        this.x = x;
    }
    public boolean collidesWith(CollisionRect rect) {
        return x < rect.x + rect.width && y < rect.y + rect.height - 20 && x + width > rect.x && y + width > rect.y;
    }
}
