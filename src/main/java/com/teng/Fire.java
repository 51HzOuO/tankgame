package com.teng;

import java.awt.Graphics;

public class Fire {
    int x, y, direct;
    int speed = 1; // 子弹速度

    public Fire(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
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
        g.fill3DRect(x, y, 5, 5, false);
    }
}
