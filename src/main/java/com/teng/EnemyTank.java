package com.teng;

import javax.swing.JPanel;

public class EnemyTank extends Tank {

    public EnemyTank(int x, int y, int direct, JPanel panel) {
        super(x, y, direct, panel);
    }

    @Override
    public void fire() {
        Fire bullet;

        // 实现特定于 EnemyTank 的射击逻辑
        // 例如，EnemyTank 可以在特定条件下自动射击
        // 这里只是一个简单的实现示例
        switch (direct) {
            case 0:
                bullet = new Fire(x + 35, y, direct);
                break;
            case 1:
                bullet = new Fire(x + 70, y + 35, direct);
                break;
            case 2:
                bullet = new Fire(x + 35, y + 70, direct);
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

    // 可以添加更多 EnemyTank 特有的方法和属性
    // 例如 AI 控制的逻辑等
}
