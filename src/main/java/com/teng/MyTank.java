package com.teng;

public class MyTank {
    private int x;
    private int y;
    private int direct;

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

    public MyTank(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }
}
