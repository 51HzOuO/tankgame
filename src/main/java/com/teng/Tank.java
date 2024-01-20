package com.teng;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

// 抽象类 Tank
public abstract class Tank {
    static private Panel_ panel_;
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

    public static void setPanel_(Panel_ panel_) {
        Tank.panel_ = panel_;
    }

    public boolean isTouch() {
        List<Tank> allTanks = new Vector<>();
        for (Tank tank : panel_.enemyTanks) {
            allTanks.add(tank);
        }
        allTanks.add(panel_.myTank);

        allTanks.remove(this);
        Rectangle thisTankRect = getTankRectangle(this);

        for (Tank tank : allTanks) {
            Rectangle otherTankRect = getTankRectangle(tank);

            if (thisTankRect.intersects(otherTankRect)) {
                adjustTankPosition(this);  // 调整坦克位置
                return true; // 检测到碰撞
            }
        }
        return false; // 未检测到碰撞
    }


    public static Rectangle getTankRectangle(Tank tank) {
        int tankWidth, tankHeight;
        int buffer = 0; // 缓冲区大小

        if (tank.direct == 0 || tank.direct == 2) { // 向上或向下
            tankWidth = 70 + buffer;
            tankHeight = 100 + buffer;
        } else { // 向左或向右
            tankWidth = 100 + buffer;
            tankHeight = 70 + buffer;
        }
        return new Rectangle(tank.x - buffer / 2, tank.y - buffer / 2, tankWidth, tankHeight);
    }

    private void adjustTankPosition(Tank tank) {
        int stepBack = 1; // 后退步长

        switch (tank.direct) {
            case 0: // 向上
                tank.y += stepBack;
                break;
            case 1: // 向右
                tank.x -= stepBack;
                break;
            case 2: // 向下
                tank.y -= stepBack;
                break;
            case 3: // 向左
                tank.x += stepBack;
                break;
        }
    }


//    public boolean isTouch() {
//        List<Tank> allTanks = new Vector<>();
//        for (Tank tank : panel_.enemyTanks) {
//            allTanks.add(tank);
//        }
//        allTanks.add(panel_.myTank);
//
//        allTanks.remove(this);
//        for (Tank tank : allTanks) {
//            switch (this.direct) {
//                case 0:
//                    if (tank.direct == 0 || tank.direct == 2) {
//
//                    }
//                case 1:
//                    if (tank.direct == 1 || tank.direct == 3) {
//
//                    }
//                case 2:
//                    if (tank.direct == 0 || tank.direct == 2) {
//
//                    }
//                case 3:
//                    if (tank.direct == 1 || tank.direct == 3) {
//
//                    }
//            }
//        }
//    }

    public void moveUp(int RATE) {

        if (y <= 0 || isTouch()) {
            return;
        }
        y -= RATE;
    }

    public void moveDown(int RATE) {
        if (y >= panel.getHeight() - 100 || isTouch()) {
            return;
        }
        y += RATE;
    }

    public void moveLeft(int RATE) {
        if (x <= 0 || isTouch()) {
            return;
        }
        x -= RATE;
    }

    public void moveRight(int RATE) {
        if (x >= panel.getWidth() - 100 || isTouch()) {
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
