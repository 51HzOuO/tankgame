package com.teng;

import javax.swing.JPanel;

public class MyTank extends Tank {

    public MyTank(int x, int y, int direct, JPanel panel) {
        super(x, y, direct, panel);
    }

    @Override
    public void fire() {

        Fire bullet;
        if (panel instanceof Panel_) {
            if (((Panel_) panel).bullets.size() == ((Panel_) panel).bulletLimit) {
                return;
            }
        }
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
