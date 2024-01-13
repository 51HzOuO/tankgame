package com.teng;

public class Tank {
    private int x;
    private int y;
    private int direct;

    public void moveUp(int RATE) {
        y -= RATE;
    }

    public void moveDown(int RATE) {
        y += RATE;
    }

    public void moveLeft(int RATE) {
        x -= RATE;
    }

    public void moveRight(int RATE) {
        x += RATE;
    }

    public int getX() {
        return x;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public Tank(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }
}
