package com.teng;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Fire implements Runnable {
    int x, y, direct;
    int speed = 1; // 子弹速度
    JPanel panel;

    public Fire(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        // 移动逻辑...
        while (shouldContinueMoving()) {
            move();
            try {
                Thread.sleep(5); // 控制子弹移动速度
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Panel_ p = (Panel_) panel;
        p.bullets.remove(this);
        System.out.println("子弹线程结束");
    }

    private boolean shouldContinueMoving() {
        // 根据子弹位置和方向判断是否继续移动
        // 示例条件，需要根据您的游戏逻辑调整
        return x >= 0 && x <= panel.getWidth() && y >= 0 && y <= panel.getHeight();
    }

    public void move() {
        // 根据方向更新子弹位置
        switch (direct) {
            case 0:
                y -= speed;
                break;
            case 1:
                x += speed;
                break;
            case 2:
                y += speed;
                break;
            case 3:
                x -= speed;
                break;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 5, 5);
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
