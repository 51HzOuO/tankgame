package com.teng;

import javax.swing.JPanel;

public abstract class Tank {
    protected int x;
    protected int y;
    protected int direct;
    protected JPanel panel;

    public Tank(int x, int y, int direct, JPanel panel) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.panel = panel;
    }

    public void moveUp(int RATE) {
        if (y <= 0) {
            return;
        }
        y -= RATE;
    }

    public void moveDown(int RATE) {
        if (y >= panel.getHeight() - 100) {
            return;
        }
        y += RATE;
    }

    public void moveLeft(int RATE) {
        if (x <= 0) {
            return;
        }
        x -= RATE;
    }

    public void moveRight(int RATE) {
        if (x >= panel.getWidth() - 100) {
            return;
        }
        x += RATE;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    // 抽象方法，子类必须实现
    public abstract void fire();
}
