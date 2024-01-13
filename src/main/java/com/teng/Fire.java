package com.teng;

import java.awt.Graphics;

public class Fire implements Runnable {
    int x, y, direct;
    int speed = 20;

    public Fire(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public void draw(Graphics g) {
        g.fill3DRect(x, y, 5, 5, false);
    }

    @Override
    public void run() {
        while (true) {
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

            // 检查子弹是否超出屏幕范围
            if (x < 0 || x > 1200 || y < 0 || y > 800) {
                break; // 退出循环，结束线程
            }

            try {
                Thread.sleep(50); // 控制子弹移动速度
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
