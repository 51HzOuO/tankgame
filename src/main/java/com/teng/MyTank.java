package com.teng;

import javax.swing.*;

public class MyTank extends Tank {

    boolean isLive = true;

    public MyTank(int x, int y, int direct, JPanel panel) {
        super(x, y, direct, panel);
    }

    // 来自Tank的抽象方法
    @Override
    public void fire() {

        // 类型为Fire的bullet变量
        Fire bullet;
        // 如果子弹的数量达到了子弹的限制则直接退出不发射子弹
        if (panel instanceof Panel_) {
            if (((Panel_) panel).bullets.size() == ((Panel_) panel).bulletLimit) {
                return;
            }
        }
        // 否则根据方向发射子弹
        switch (direct) {
            case 0:
                bullet = new Fire(x + 35, y, direct);
                break;
            case 1:
                bullet = new Fire(x + 95, y + 35, direct);
                break;
            case 2:
                bullet = new Fire(x + 35, y + 95, direct);
                break;
            case 3:
                bullet = new Fire(x, y + 35, direct);
                break;
            default:
                return;
        }
        bullet.setPanel(this.panel);
        if (panel instanceof Panel_) {
            ((Panel_) panel).addBullet(bullet);
        }
        new Thread(bullet).start();
    }

    // 这里可以添加更多 MyTank 特有的方法和属性
}
